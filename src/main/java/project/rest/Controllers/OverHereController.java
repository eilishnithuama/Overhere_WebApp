package project.rest.Controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import project.rest.Entities.AndroidUser;
import project.rest.Entities.Carer;
import project.rest.Entities.Doctor;
import project.rest.Entities.Fall;
import project.rest.Entities.GPSLocation;
import project.rest.Entities.Heart;
import project.rest.Entities.UserEntity;
import project.rest.Entities.UserEvent;
import project.rest.Repository.CarerRepository;
import project.rest.Repository.DoctorRepository;
import project.rest.Repository.GPSLocationRepository;
import project.rest.Repository.HeartRateRepository;
import project.rest.Repository.UserEventRepository;
import project.rest.Service.FallService;
import project.rest.Service.UserService;

@Controller
@SessionAttributes(value={"user","loggedIn"})
public class OverHereController {
	//private boolean loggedIn =false;
	
	@Autowired
	FallService fallService;
	@Autowired
	GPSLocationRepository gpsRepo;
	@Autowired
	HeartRateRepository heartRepo;
	@Autowired
	UserService userService;

	@Autowired
	DoctorRepository doctorRepo;
	
	@Autowired
	CarerRepository carerRepo;
	
	@Autowired
	UserEventRepository userEventRepo;
	
	UserEntity loggedUser;
	
	//Website Homepage
	@RequestMapping("/")
    String home(Model model,HttpSession session) 
	{
		//User has logged in
		if(model.containsAttribute("loggedIn"))
		{			
			boolean loggedIn = (boolean) session.getAttribute("loggedIn");
			//System.out.println(loggedUser);
			if(loggedIn){
				Set<UserEntity> pl = loggedUser.getDoctor().getPatientList();
				//pl.remove(loggedUser);
				int patientId = 0;
				if(loggedUser.getType() == 3)
				{
					/* Temporarily set the carers address as their patients address so 
					that it can be used to display the map */
					Carer carer  = carerRepo.findByName(loggedUser.getName());
					loggedUser.setHomeAddress(carer.getUser_id().getHomeAddress());
					patientId = carer.getUser_id().getUser_id();
					pl.clear();
				}
				else if(loggedUser.getType() == 2){
					//Map is not used in doctor view so a real location is not needed
					GPSLocation blankAddress = new GPSLocation();
					blankAddress.setLatitude(0);
					blankAddress.setLongitude(0);
					loggedUser.setHomeAddress(blankAddress);
					
					System.out.println("Users in patientList");
					for(UserEntity u: pl){
						System.out.println(u);
						System.out.println(u.getCarer_id());
					}
					
					System.out.println("Users in db");
					for(UserEntity u: userService.findAll()){
						System.out.println(u);
						System.out.println(u.getCarer_id());
					}
					
					
				}
				else
				{
					pl.clear(); // Don't pass doctor patients
				}
				model.addAttribute("patientList",pl);
				model.addAttribute("patientId",patientId);
				model.addAttribute("user", loggedUser);
				
				return "index";
			}
			else {
				loggedUser = new UserEntity();
				model.addAttribute("loggedIn", false);
				if(!model.containsAttribute("user"))
					model.addAttribute("user", loggedUser);
				
				return "signIn";
			}
		}
		else{
			
			loggedUser = new UserEntity();
			model.addAttribute("loggedIn", false);
			if(!model.containsAttribute("user"))
				model.addAttribute("user", loggedUser);
			
			return "signIn";
		}
    }
	
	//Website User Interaction
    @CrossOrigin
    @RequestMapping(value="/user",method = RequestMethod.POST)
    public String signIn(Model model,@ModelAttribute UserEntity user){
    	
    	UserEntity userDb = userService.findByUsername(user.getUsername());
    	if(userDb != null && userDb.getPassword().equals(user.getPassword())){
    		loggedUser = userDb;
    		//boolean loggedIn = true;
    		model.addAttribute("loggedIn",true);
    		return "redirect:/";
    	}
    	else {
    		
    		loggedUser = new UserEntity();
    		model.addAttribute("user", loggedUser);
    		model.addAttribute("error", "Sign in Failed. Please try again");
    		return "signIn";
    	}
    }
	
	//Android
    @RequestMapping(value="/saveAccel",method=RequestMethod.POST)
    public ResponseEntity<Fall> fallprocess(@RequestBody Fall fall){
    	if(fall.getUserid() != 0)
    		fallService.save(fall);
    	
    	return new ResponseEntity<Fall>(fall,HttpStatus.OK);
    }
    
    //Android
    @RequestMapping(value="/saveGPS",method=RequestMethod.POST)
    public ResponseEntity<GPSLocation> gpsprocess(@RequestBody GPSLocation gps){
        //Check User Id first
    	if(gps.getUser_id() != 0){
            
	    	UserEntity ue = userService.findByUserId(gps.getUser_id());
            //Valid user id
	    	if(ue!=null){
                //Check if the user has a home address set
                if(ue.getHomeAddress() == null){
                    System.out.println("Saving User");
                    ue.setHomeAddress(gps);
                    userService.save(ue);
                }
                //Save the GPS value
                gpsRepo.save(gps); 
            }
    	}
    	return new ResponseEntity<GPSLocation>(gps,HttpStatus.OK);
    }
    
    //Android
    @RequestMapping(value="/saveHeart",method=RequestMethod.POST)
    public ResponseEntity<Heart>heartprocess(@RequestBody Heart heart){
    	heartRepo.save(heart);
    	
    	return new ResponseEntity<Heart>(heart,HttpStatus.OK);	
    }
    
    //Android
    @RequestMapping(value="/saveUserEvent",method=RequestMethod.POST)
    public ResponseEntity<UserEvent>userEventprocess(@RequestBody UserEvent ue){
    	userEventRepo.save(ue);
    	return new ResponseEntity<UserEvent>(ue,HttpStatus.OK);
    }
    
    //Website
    @CrossOrigin
    @RequestMapping(value = "/findall")
    public @ResponseBody List<Fall> findAll(@RequestParam int userid,HttpSession session){
    	if(session.getAttribute("loggedIn") != null){
            if((boolean)session.getAttribute("loggedIn"))
            {
        	   return fallService.findAllByUserid(userid);
            }
    	}

    	return null;        
    }
    
    //Website
    @CrossOrigin
    @RequestMapping(value = "/findLatest")
    public @ResponseBody Fall findLatest(@RequestParam int userid,HttpSession session){
    	if(session.getAttribute("loggedIn") != null){
    		if((boolean)session.getAttribute("loggedIn"))
        	{
            	Set<UserEntity> list = loggedUser.getDoctor().getPatientList();
            	for(UserEntity ue : list)
            	{
            		if(ue.getUser_id() == userid)
            			return fallService.findLatest(userid);
            	}  
        	}
    	}
    	
    	return null;

    }
    
    //Android
    @RequestMapping(value="/findByUsername",method=RequestMethod.POST)
    public ResponseEntity<AndroidUser> process(@RequestBody AndroidUser au){
    	UserEntity ue = userService.findByUsername(au.getUsername());
    	if(ue != null && ue.getPassword().equals(au.getPassword())){
	    	au.setHomeAddress(ue.getHomeAddress());
	    	au.setName(ue.getName());
	    	au.setUser_id(ue.getUser_id());
    		String carerNumbers = "";
    		for(Carer c: ue.getCarer_id()){
    			carerNumbers+= c.getMobile() + ",";
    		}
    		au.setCarerNumber(carerNumbers);
	    	
	    	System.out.println(au);
	    	return new ResponseEntity<AndroidUser>(au,HttpStatus.OK);
    	}
    	else
    	{
    		System.out.println("Into else");
    		return new ResponseEntity<AndroidUser>(au,HttpStatus.OK);
    	}
    		
    }
    //Website
    @CrossOrigin
    @RequestMapping(value = "/findLatestGPS")
    public @ResponseBody GPSLocation findLatestGPS(@RequestParam int userid,
    												HttpSession session){
    	if(session.getAttribute("loggedIn") != null){
    		if((boolean)session.getAttribute("loggedIn")){
        		List<GPSLocation> list = gpsRepo.findLatestGPSLocationEntry(userid);
        		if(list.size() != 0)
        		{
        			return list.get(0);
        		}
        		else
        			return new GPSLocation();
        	}
    	}
    	
    	return null;
    }
    
    //Android
    @CrossOrigin
    @RequestMapping(value = "/findHomeAddess")
    public @ResponseBody GPSLocation findHomeAddess(@RequestParam String username){
    	return (userService.findByUsername(username)).getHomeAddress();
    }
    
    //Website User Interaction
    @RequestMapping(value="/login")
    public String login(Model model){
    	
    	model.addAttribute("user", new UserEntity());
    	return "signIn";
    }
    
    //Website User Interaction
    @RequestMapping(value="/view")
    public String viewDetails(Model model, @RequestParam int user_id,HttpSession session){
    	
    	if(session.getAttribute("loggedIn") != null){
    		if((boolean)session.getAttribute("loggedIn") && loggedUser.getType() == 2)
        	{
        		model.addAttribute("user",loggedUser);
        		if(!loggedUser.getDoctor().getPatientList().isEmpty()){
        			for(UserEntity u :loggedUser.getDoctor().getPatientList())
        			{
        				if(u.getUser_id() == user_id){
        					model.addAttribute("userEvents", userEventRepo.findByUserid(user_id));
        					model.addAttribute("patient",u);
        				}
        			}
        		}
        		
        		if(model.containsAttribute("patient")){
        			return "viewDetails";
        		}	
        	}
    	}

    	return  "redirect:/";
    	
    }
    
    //Website User Interaction
    @RequestMapping(value="/addPatient")
    public String addPatient(Model model,HttpSession session){
    	if(session.getAttribute("loggedIn") != null){
    		if((boolean)session.getAttribute("loggedIn")){
            	model.addAttribute("newPatient", new UserEntity());
            	return "addPatient";
        	}
    	}
    	
    	return "redirect:/";
    	
    }
    //Website User Interaction
    @RequestMapping(value="/addCarer")
    public String addCarer(Model model,@RequestParam int user_id,HttpSession session){
    	
    	if(session.getAttribute("loggedIn") != null){
    		if((boolean)session.getAttribute("loggedIn"))
    		{
        		Carer carer = new Carer();
            	if(user_id != 0)
            	{
            		for(UserEntity u : loggedUser.getDoctor().getPatientList())
            		{
            			if(u.getUser_id() == user_id){
            				carer.setUser_id(u);
            			}		
            		}
            	}
            	
            	model.addAttribute("carer",new UserEntity());
            	System.out.println("Logged User Doctor:" + loggedUser.getDoctor());
            	return "addCarer";
        	}
    	}
    	
    	return "redirect:/";
    	
    }
    
    //Website User Interaction
    @RequestMapping(value="/newPatient",method=RequestMethod.POST)
    public String newPatient(Model model,@ModelAttribute UserEntity newPatient,HttpSession session){
    	if(session.getAttribute("loggedIn") != null){
    		if((boolean)session.getAttribute("loggedIn")){		
    	    	try
    	    	{
    	    		if(newPatient.getHomeAddress().getLatitude() == 0 
    	    				&& newPatient.getHomeAddress().getLongitude() == 0){
    	    			model.addAttribute("newPatient", new UserEntity());
        	    		model.addAttribute("error","Could not find your address. Please try again."
        	    				+ "If your address could not be found, please drop a pin on the location on"
        	    				+ " Google Maps to find the"
        	    				+ "latitude and longitude values. Enter these into the latitude "
        	    				+ "and longitude text boxes on the page.");
        	    		return "addPatient";
    	    		}
    	    		//Check if user already exists
    	    		boolean exists = false;
    	    		for(UserEntity user : userService.findAll()){
    	    			if(user.getName() == newPatient.getUsername()){
    	    				exists = true;
    	    			}
    	    		}
    	    		
    	    		if(!exists){
    	    			newPatient.setType(1);
        	    		newPatient.setCarer_id(new ArrayList<Carer>());
        	    		userService.save(newPatient);
        	    		Doctor d = loggedUser.getDoctor();
        	    		Set<UserEntity> pl = loggedUser.getDoctor().getPatientList();
        		    	pl.add(newPatient);
        		    	d.setPatientList(pl);
        		    	System.out.println("Saving Doctor");
        		    	//saving the doctor automatically adds the patient
        		    	doctorRepo.save(d);
        		    	System.out.println("Updating Patient");
        		    	newPatient.setDoctor(d);
        		    	userService.save(newPatient);
        		    	
        		    	//Add the home address as the first entry in the GPS table
        		    	gpsRepo.save(newPatient.getHomeAddress());
        		    	
        		    	//Update the user
        		    	loggedUser = userService.findByUsername(loggedUser.getUsername());
        		    	
        		    	model.addAttribute("response","Successful add");
        		    	model.addAttribute("newPatient", new UserEntity());
        		    	
        		    	return "addPatient";
    	    		}
    	    		else{
    	    			model.addAttribute("newPatient", new UserEntity());
        	    		model.addAttribute("error","Patient With Username already exists. Please enter another.");
        	    		return "addPatient";
    	    		}
    	    		
    	    	
    	    	}
    	    	catch(Exception e){
    	    		e.printStackTrace();
    	        	model.addAttribute("newPatient", new UserEntity());
    	    		model.addAttribute("error","Error adding Patient. Please try again!");
    	    		return "addPatient";
    	    	}
    		}
    	}
		
    	return "redirect:/";
    }
    
    //Website User Interaction
    @RequestMapping(value="newCarer",method=RequestMethod.POST)
    public String newCarer(@ModelAttribute("carer") UserEntity carer,Model model,HttpSession session)
    {
    	if(session.getAttribute("loggedIn") != null){
    		if((boolean)session.getAttribute("loggedIn"))
        	{
    			boolean exists = false;
	    		for(UserEntity user : userService.findAll()){
	    			if(user.getName() == carer.getUsername()){
	    				exists = true;
	    			}
	    		}
	    		
	    		if(!exists)
	    		{
	    			Carer c = new Carer();
	        		c.setMobile(carer.getCarer_id().get(0).getMobile());
	        		c.setName(carer.getName());
	            	carer.setType(3);
	            	for(UserEntity patient : userService.findAll())
	            	{
	            		if(patient.getUser_id() == carer.getUser_id())
	            		{	
	            			try
	            			{
	                        	carer.setUser_id(0);               			
	                			c.setUser_id(patient);  
	                			carer.setCarer_id(new ArrayList<Carer>());
	                			List<Carer> carerList = patient.getCarer_id();
	                			carerList.add(c);
	                			
	            				Doctor d = loggedUser.getDoctor();
	            	    		Set<UserEntity> pl = loggedUser.getDoctor().getPatientList();
	            	    		pl.remove(patient); // remove the old patient reference
	                			patient.setCarer_id(carerList);
	                			System.out.println("Saving Patient UE");
	                			userService.save(patient);
	                			System.out.println("Saving Carer UE");
	                			userService.save(carer);
	                			
	            		    	pl.add(patient);
	            		    	pl.add(carer);
	            		    	d.setPatientList(pl);
	            		    	System.out.println("Saving Doctor");
	            		    	doctorRepo.save(d);	            		   

	            		    	//Update the user from the database
	                			loggedUser = userService.findByUsername(loggedUser.getUsername());
	                			
	                			model.addAttribute("response", "Success");
	                        	model.addAttribute("user", loggedUser);
	                        	return "addCarer";
	                    		
	                    	}
	                    	catch(Exception e){
	                    		e.printStackTrace();
	                    		model.addAttribute("error","Error adding Carer. Please try again.");
	                    		return "addCarer";
	                    	}
	            					
	            		}
	            	}
	    		}
	    		
	    		else{
	    			model.addAttribute("error","Carer with username supplied already exists. Please select another.");
	    			return "addCarer";
	    		}
        	}	
    	}
    		
    	return "redirect:/";
    	
    }
    
    //Website User Interaction
    @RequestMapping(value="/logout")
    public String logout(Model model, WebRequest request, SessionStatus status){
    	//loggedIn = false;
    	loggedUser = null;
    	status.setComplete();
    	return "redirect:/";
    	
    }
}
