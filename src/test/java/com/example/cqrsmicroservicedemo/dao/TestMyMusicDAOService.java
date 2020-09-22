package com.example.cqrsmicroservicedemo.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cqrsmicroservicedemo.dto.MyMusicDTO;
import com.example.cqrsmicroservicedemo.util.TextUtil;

@SpringBootTest
public class TestMyMusicDAOService {

	@Autowired 
	private MyMusicDAOService dao;
	
	@Test
	public void testCount() {
		System.out.println(dao.count());
		assertTrue(true);
	}
	
	@Test
	public void testSimpleDateFormat() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		
		System.out.println(sdf.format(date));
		assertTrue(true);
	}
	
	@Test
	public void testCreateInsertUpdateAndDeleteMyMusic() throws Exception {
		dao.dropTable();
		System.out.println("DROP TABLE OK");
		assertTrue(true);
		dao.createTable();
		System.out.println("CREATE TABLE OK");
		assertTrue(true);
		
		Object[][] objlist = new Object[][] {
			{1, "Blinding Light", 		"Weeknd", 	"POP", "2019-11-29"},
			{2, "Happy", 				"Pharrell", "POP", "2013-11-21"},
			{3, "Rolling in the Deep.", "Adele", 	"POP", "2010-11-29"},
			{4, "Moves Like Jagger", 	"Maroon 5", "Disco", "2011-06-21"},
			{5, "Just The Way You Are", "Bruno Mars", "POP", "2010-07-20"}
		}; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println("Begin to Insert new Data");
		for (Object[] obj:objlist) {
			MyMusicDTO dto = new MyMusicDTO();
			dto.setMusicId((int) obj[0]);
			dto.setSong(String.valueOf(obj[1]));
			dto.setArtist(String.valueOf(obj[2]));
			dto.setGenre(String.valueOf(obj[3]));
			dto.setReleaseDate(sdf.parse(String.valueOf(obj[4])));
			
			dao.addNewMusic(dto);
		}

		List<MyMusicDTO> list = dao.getAll();
		TextUtil.printJson(list);
						
		System.out.println("test find music by id = 3");
		MyMusicDTO dto = dao.findByMusicId(3);
		TextUtil.printJson(dto);
		dto.setGenre("Jazz");
		
		System.out.println("test update music id : 3 to jazz");
		dao.updateMyMusic(dto);
		
		TextUtil.printJson( dao.findByMusicId(3) );
		
		System.out.println("test delete music id : 3");
		dao.removeMyMusicById(3);
		TextUtil.printJson(dao.getAll());
		
		assertTrue(true);
	}

	@Test
	public void testGetAllMyMusic() throws Exception {
		List<MyMusicDTO> list = dao.getAll();
		TextUtil.printJson(list);
		
		assertTrue(true);
	}
	
}
