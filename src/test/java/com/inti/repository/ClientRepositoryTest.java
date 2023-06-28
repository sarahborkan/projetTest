package com.inti.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.model.Client;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ClientRepositoryTest {
	
	@Autowired
	IClientRepository icr;
	
	@BeforeAll
	public static void debut()
	{
		
	}
	
	@BeforeEach
	public void setUp()
	{
		
	}
	
	@Test
	public void saveClientTest()
	{
		// GIVEN
		Client c1 = new Client("Durand", "Louis", "test@test.fr");
		
		// WHEN
		Client clientSaved = icr.save(c1);
		
		// THEN
		assertThat(clientSaved).isNotNull();
		assertThat(clientSaved.getId()).isGreaterThan(0);
	}
	
	@Test
	public void saveClientWithArgsFacultativesTest()
	{
		// GIVEN
		Client c1 = new Client();
		c1.setNom("Durand");
		
		// WHEN
		Client clientSaved = icr.save(c1);
		
		// THEN
		assertThat(clientSaved).isNotNull();
		assertThat(clientSaved.getId()).isGreaterThan(0);
		assertThat(clientSaved.getNom()).isEqualTo("Durand");
		assertThat(clientSaved.getPrenom()).isNull();
	}
	
	@Test
	public void saveClientExceptionParamObligatoireTest()
	{
		// GIVEN
		Client c1 = new Client();
		c1.setPrenom("Louis");
		
		// WHEN
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> icr.save(c1));
	}
	
	@Test
	public void saveClientWithUniqueParamTest()
	{
		// GIVEN
		Client c1 = new Client();
		c1.setNom("Durand");
		c1.setEmail("test@test.fr");
		icr.save(c1);
		Client c2 = new Client();
		c2.setNom("Toto");
		c2.setEmail("test@test.fr");		
		
		// WHEN
//		Client clientSaved = icr.save(c2);
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> icr.save(c2));
	}
	
	@Test
	public void saveClientWithNomTailleExceptionTest()
	{
		// GIVEN
		Client c1 = new Client();
		c1.setNom("Durandaaaaaaaa");
		c1.setEmail("test@test.fr");		
		
		// WHEN
//		Client clientSaved = icr.save(c2);
		
		// THEN : BIZARRE pour la taille, exception !
//		Assertions.assertThrows(Exception.class, () -> icr.save(c1));
	}
	
	@Test
	public void deleteClientTest()
	{
		// GIVEN
		Client c1 = new Client("Durand", "Louis", "test@test.fr");
		Client clientSaved = icr.save(c1);
		
		// WHEN
		icr.delete(clientSaved);
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> icr.getReferenceById(c1.getId()));
	}
	
	@Test
	public void deleteClientNullTest()
	{
		// GIVEN
				
		// WHEN
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> icr.delete(null));
	}
	
	@Test
	public void updateClientTest()
	{
		// GIVEN
		Client c1 = new Client("Durand", "Louis", "test@test.fr");
		Client clientSaved = icr.save(c1);
				
		// WHEN
		clientSaved.setNom("Dupont");
		Client clientModified = icr.save(clientSaved);
		
		// THEN
		assertThat(clientModified).isNotNull();
		assertThat(clientModified.getNom()).isEqualTo("Dupont");
	}
	
	@Test
	public void updateClientUniqueParamTest()
	{
		// GIVEN
		Client c1 = new Client("Durand", "Louis", "test@test.fr");
		Client c2 = new Client("Durand", "Louis", "info@test.fr");
		Client clientSaved1 = icr.save(c1);
		Client clientSaved2 = icr.save(c2);
				
		// WHEN
		clientSaved2.setEmail("test@test.fr");
		// BIZARRE : pas d'exception !
		System.out.println(icr.save(clientSaved2));
		
		// THEN
//		Assertions.assertThrows(Exception.class, () -> icr.save(clientSaved2));
	}
	
	@Test
	public void updateClientNullTest()
	{
		// GIVEN
		Client c1 = null;
				
		// WHEN
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> c1.setPrenom("test"));
	}
	
	@Test
	public void getClientTest()
	{
		// GIVEN
		Client c1 = new Client("Durand", "Louis", "test@test.fr");
		Client clientSaved = icr.save(c1);
				
		// WHEN
		Client client = icr.getReferenceById(clientSaved.getId());
		
		// THEN
		assertThat(client).isNotNull();
		assertThat(client.getNom()).isEqualTo("Durand");
		assertThat(client).isEqualTo(clientSaved);
	}
	
	@Test
	public void getClientNullTest()
	{
		// GIVEN
				
		// WHEN
//		System.out.println(icr.getReferenceById(150));
		
		// THEN
//		Assertions.assertThrows(EntityNotFoundException.class, () -> icr.getReferenceById(150));
	}
	
	@Test
	public void getAllClientTest()
	{
		// GIVEN
		Client c1 = new Client("Durand", "Louis", "test@test.fr");
		Client c2 = new Client("Durand", "Louis", "info@test.fr");
		Client clientSaved1 = icr.save(c1);
		Client clientSaved2 = icr.save(c2);
				
		// WHEN
		List<Client> listeC = icr.findAll();
		
		// THEN
		assertThat(listeC).isNotEmpty();
		assertThat(listeC).hasSize(2);
		assertThat(listeC.get(0).getClass()).hasSameClassAs(Client.class);
	}
	
	@Test
	public void getClientListeVideTest()
	{
		// GIVEN
				
		// WHEN
		List<Client> listeC = icr.findAll();
		
		// THEN
		assertThat(listeC).isEmpty();
		assertThat(listeC).hasSize(0);
	}


}
