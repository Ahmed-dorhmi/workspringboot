/**
 * 
 */
package com.moi.project.japrest;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.moi.project.model.Client;


/**
 * @author moi
 *
 */
@RepositoryRestResource(path="abc",collectionResourceRel ="clients")
public interface Exemple  extends PagingAndSortingRepository<Client, Long>{
	List<Client> findByNom(@Param("nom")String nom);
}
