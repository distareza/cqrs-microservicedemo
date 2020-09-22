package com.example.cqrsmicroservicedemo.solr;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cqrsmicroservicedemo.dto.MyMusicDTO;

@SpringBootTest
public class TestMyMusicSolrService {

	@Autowired
	private MyMusicSolrService service;
	
	@Test
	public void testSelectMusic() {
		service.selectMyMusic(1);
		
		assertTrue(true);
	}
	
	@Test
	public void testInsertSolrData() throws Exception {
		String[][] mymusiclist = new String[][] 
			{
				{"1", 	"Bob Dylan",	"Like a Rolling Stone", "Rock"},
				{"2",	"The Rolling Stones", "(I Can't Get No) Satisfaction", "Jazz"},
				{"3",	"John Lennon",	"Imagine", "Pop"},
				{"4",	"Marvin Gaye",	"What's Going On", "Rege"},
				{"5",	"Aretha Franklin",	"Respect",	"Instrumental"},
				{"6",	"The Beach Boys",	"Good Vibrations", "Classic"},
				{"7",	"Chuck Berry",	"Johnny B. Goode", "Rap"},
				{"8",	"The Beatles",	"Hey Jude",	"Pop"},
				{"9",	"Nirvana",	"Smells Like Teen Spirit",	"alternative"},
				{"10",	"Ray Charles",	"What'd I Say", "RnB"},
			};
		
		
		for (String[] music:mymusiclist) {
			MyMusicDTO dto = new MyMusicDTO();
			dto.setMusicId(Integer.parseInt(music[0]));
			dto.setSong(music[1]);
			dto.setArtist(music[2]);
			dto.setGenre(music[3]);
			dto.setReleaseDate(new Date());
			
			service.insertMusic(dto);
		}
		
		assertTrue(true);
	}
	
	
	public void testUpdateSolrData() throws Exception {
		
	}
	
	@Test
	public void testDeleteAllSolrData() throws Exception {
		service.deleteAllMusic();
	}
	
}
