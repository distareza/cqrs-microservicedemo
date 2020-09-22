package com.example.cqrsmicroservicedemo.solr;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.cqrsmicroservicedemo.dto.MyMusicDTO;
import com.example.cqrsmicroservicedemo.util.TextUtil;

@Service
public class MyMusicSolrService {

	private RestTemplate restTemplate = new RestTemplate();
	
	@Value("${solr.url}")
	private String solrUrl;
	
	public void selectMyMusic(int musicId) {
		
		String url = String.format("%s/mymusic/select?q=*:*", solrUrl);
		System.out.println( restTemplate.getForObject(url, String.class) );
		
	}
	
	public void insertMusic(MyMusicDTO dto) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String releaseDate = "";
		if (dto.getReleaseDate()!=null)
			releaseDate = sdf.format(dto.getReleaseDate());
		
		String json = String.format(
						"[{ \"music_id\": %d, " +
						"	\"song\": \"%s\", " + 
						"	\"artist\": \"%s\", " + 
						"	\"genre\": \"%s\", " + 
						"	\"release_date\": \"%s\" }] ",
				dto.getMusicId(), dto.getSong(), dto.getArtist(), dto.getGenre(), releaseDate);
		System.out.println(json);
		
		HttpEntity<String> entity = new HttpEntity<>(json, headers);
		String url = String.format("%s/mymusic/update?commit=true", solrUrl);
		System.out.println( restTemplate.postForObject(url, entity, String.class) );		
	}
	
	public void deleteMusic(int musicId) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_XML_VALUE);

		String xml = String.format("<delete><query>music_id:%d</query></delete>", musicId);
		HttpEntity<String> entity = new HttpEntity<>(xml, headers);
		String url = String.format("%s/mymusic/update?commit=true", solrUrl);
		System.out.println( restTemplate.postForObject(url, entity, String.class) );		
		
	}
	
	public void deleteAllMusic() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_XML_VALUE);
		
		String xml = "<delete><query>*:*</query></delete>";
		HttpEntity<String> entity = new HttpEntity<>(xml, headers);
		String url = String.format("%s/mymusic/update?commit=true", solrUrl);
		System.out.println( restTemplate.postForObject(url, entity, String.class) );		
		
	}
	
}
