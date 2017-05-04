package project.rest.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import project.rest.Entities.GPSLocation;

public interface GPSLocationRepository extends JpaRepository<GPSLocation,Long> {
	
	GPSLocation findById(int id);
	List<GPSLocation> findByLongitude(float longitude);
	List<GPSLocation> findByLatitude(float latitude);
	List<GPSLocation> findByUserid(int user_id);
	
	@Query(nativeQuery = true,value ="SELECT * FROM GPSLocation where USER_ID = :id ORDER BY timestamp DESC")
	List<GPSLocation> findLatestGPSLocationEntry(@Param("id") int userid);

}
