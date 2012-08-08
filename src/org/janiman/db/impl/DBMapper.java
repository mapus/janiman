package org.janiman.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.anidb.Anime;
import net.anidb.Episode;
import net.anidb.File;
import net.anidb.Group;
import net.anidb.RelatedEpisode;

import org.janiman.db.IDBMapper;
import org.janiman.db.impl.et.JAMEpisode;
import org.janiman.db.impl.et.MALEpisode;
import org.janiman.parser.myanimelist.MALAnime;

public class DBMapper implements IDBMapper {
	private static DBMapper instance = new DBMapper();

	Connection conn;
	Statement stat;
	PreparedStatement prep;

	private DBMapper() {

		try {

			Class.forName("org.sqlite.JDBC");
			conn=DriverManager.getConnection("jdbc:sqlite:test.db");
			stat = conn.createStatement();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setUpDatabase();
		setUpDatabaseADBFile();
		setUpDatabaseADBEpisode();
		setUpDatabaseADBAnime();
		setUpDatabaseADBCategory();
	}

	private void setUpDatabase() {
		/**
		 * int id; String title; String english; String synonyms; int episodes;
		 * String type; String status; String start_date; String end_date;
		 * String synopsis; String image; Double score;
		 */
		try {
			/*
			 * 	private int id;
				private String title;
				private String synopsis;
				private String type;
				private String rank;
				private String popularity_rank;
				private String image_url;
				private int episodes;
				private String status;
				private String start_date;
				private String end_date;
				private String classification;
				private String members_score;
				private int members_count;
				private String favorited_count;
				private int listed_anime_id;
				private int watched_episodes;
				private String score;
				private String watched_status;

			 */
			stat.execute("CREATE TABLE IF NOT EXISTS maldump("
					+ "id INTEGER PRIMARY KEY," + "title STRING ,"
					+ "synopsis STRING ," + "type STRING, rank STRING, popularity_rank STRING, image_url STRING, episodes INTEGER, status STRING, start_date STRING, end_date STRING, classification STRING, members_score STRING," +
							"members_count INTEGER, favorited_count STRING, listed_anime_id INTEGER, watched_episodes INTEGER, score STRING, watched_status STRING)");

			stat.execute("CREATE TABLE IF NOT EXISTS folder( id INTEGER PRIMARY KEY, folder STRING);");
			
			stat.execute("CREATE TABLE IF NOT EXISTS fileloc(absFilePath STRING," +
					" adbAnimeId LONG," +
					" ed2kHash STRING," +
					"adbEpisodeId LONG," +
					"adbFileId LONG," +
					"UNIQUE(ed2kHash));");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setUpDatabaseADBFile()
	{
		/** The file Id. */
		//private Long fileId;
		/** The episode. */
		//private Episode episode;
		/** The group. */
		//private Group group;
		/** The MyList Id. */
		//private Long myListId;
		/** The list of other episodes. */
		//private List<RelatedEpisode> otherEpisodes;
		/** The 'is deprecated' flag. */
		//private Boolean deprecated;
		/** The state. */
		//private Integer state;
		/** The size. */
		//private Long size;
		/** The ed2k hash. */
		//private String ed2k;
		/** The md5 hash. */
		//private String md5;
		/** The sha1 hash. */
		//private String sha1;
		/** The crc32 hash. */
		//private String crc32;
		/** The quality. */
		//private String quality;
		/** The source. */
		//private String source;
		/** The audio codec list. */
		//private List<String> audioCodecList;
		///** The audio bitrate list. */
		//private List<Long> audioBitrateList;
		/** The video codec. */
		//private String videoCodec;
		/** The video bitrate. */
		//private Long videoBitrate;
		/** The video resolution. */
		//private String videoResolution;
		/** The file type. */
		//private String fileType;
		/** The dub language. */
		//private String dubLanguage;
		/** The sub language. */
		//private String subLanguage;
		/** The length in seconds. */
		//private Long lengthInSeconds;
		/** The description. */
		//private String description;
		/** The aired date. */
		//private Long airedDate;
		/** The AniDB file name. */
		//private String aniDbFileName;
		try {
			stat.execute("CREATE TABLE IF NOT EXISTS adbFiles(" +
					"adbFileId LONG NOT NULL," +
					"groupId LONG," +
					"myListId LONG," +
					"state INTEGER," +
					"size LONG," +
					"ed2kHash STRING ," +
					"md5Hash STRING," +
					"sha1Hash STRING," +
					"crc32Hash STRING," +
					"quality STRING," +
					"source STRING," +
					"videoCodec STRING," +
					"videoBitrate LONG," +
					"videoResolution STRING," +
					"fileType STRING," +
					"dubLanguage STRING," +
					"lengthInSecounds LONG," +
					"description STRING," +
					"airedDate LONG," +
					"aniDBFileName STRING," +
					"UNIQUE(adbFileId));");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setUpDatabaseADBEpisode()
	{
		/*
		 * @param episodeId The episode Id.
		 * @param anime The anime.
		 * @param length The length in minutes.
		 * @param rating The rating.
		 * @param votes The votes.
		 * @param episodeNumber The episode number.
		 * @param englishTitle The english title.
		 * @param romajiTitle The romaji title.
		 * @param kanjiTitle The kanji title.
		 * @param aired The air date.
		 * */
		
		try {
			stat.execute("CREATE TABLE IF NOT EXISTS adbEpisodes(" +
					"adbanimeId LONG," +
					"epId LONG,"+
					"adbfileId LONG," +
					"adbepisodeId LONG," +
					"votes LONG," +
					"length LONG," +
					"englishTitle STRING," +
					"romajiTitle STRING," +
					"kanjiTitle STRING," +
					"aired LONG," +
					"UNIQUE(adbanimeId,adbepisodeId));");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setUpDatabaseADBAnime()
	{
		/*
		private Long animeId;
		private String year;
		private String type;
		private String romajiName;
		private String kanjiName;
		private String englishName;
		private Long episodes;
		private Long normalEpisodeCount;
		private Long specialEpisodeCount;
		private Long airDate;
		private Long endDate;
		private String url;
		private String picname;
		private Long rating;
		private Long voteCount;
		private Long tempRating;
		private Long tempVoteCount;
		private Long averageReviewRating;
		private Long reviewCount;
		private Boolean is18PlusRestricted;
		private Long animePlanetId;
		private Long annId;
		private Long allCinemaId;
		private String animeNfoId;
		private Long dateRecordUpdated;
		private Long specialsCount;
		private Long creditsCount;
		private Long otherCount;
		private Long trailerCount;
		private Long parodyCount;
		*/
		try {
			stat.execute("CREATE TABLE IF NOT EXISTS adbAnime(" +
					"animeId LONG," +
					"year STRING," +
					"type STRING," +
					"romanjiName STRING," +
					"kanjiName STRING," +
					"englishName STRING," +
					"episodes LONG," +
					"normalEpisodeCount LONG," +
					"airDate LONG," +
					"url STRING," +
					"picname STRING," +
					"rating LONG," +
					"voteCount LONG," +
					"tempRating LONG," +
					"tempVoteCount LONG," +
					"averageReviewRating LONG," +
					"reviewCount LONG," +
					"is18PlusRestricted BOOLEAN," +
					"animePlanetId LONG," +
					"annId LONG," +
					"allCinemaId LONG," +
					"animeNfoId STRING," +
					"dataRecordUpdate LONG," +
					"specialCount LONG," +
					"creditsCount LONG," +
					"otherCount LONG," +
					"trailerCount LONG," +
					"parodyCount LONG," +
					"UNIQUE(animeId));");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setUpDatabaseADBCategory()
	{
		try {
			stat.execute("CREATE TABLE IF NOT EXISTS category(" +
					"animeId LONG," +
					"category STRING," +
					"UNIQUE(animeId,category));");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isAlreadyInDatabase(long anidbId)
	{
		boolean returnValue = false;
		ResultSet set = null;
		try {
			set = stat.executeQuery("SELECT * FROM adbAnime WHERE animeId="+anidbId+";");
			if(set.next())
			{
				returnValue=true;
			}

				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return returnValue;
	}
	public void addEpisode(MALEpisode epi, java.io.File episode)
	{
		try {
			PreparedStatement prep = conn.prepareStatement("insert into episods values (?, ?, ?, ?);");
			prep.setInt(1,epi.getMalId());
			prep.setInt(2,epi.getEpnr()); 
			prep.setString(3,episode.getAbsolutePath());
			prep.setString(4,epi.getName());
			prep.addBatch();
			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addFileLoc(String absoluteFilePath,long aniDBanimeId,String ed2kHash,long aniDBepisodeId, long adbFileId )
	{
		/*
		 * 			stat.execute("CREATE TABLE IF NOT EXISTS fileloc(absFilePath STRING PRIMARY KEY," +
					" adbAnimeId LONG," +
					" ed2kHash STRING," +
					"adbEpisodeId LONG," +
					"adbFileId LONG);");
		 */
		try {
			PreparedStatement prep = conn.prepareStatement("insert into fileloc values (?, ?, ?, ?, ?);");
			prep.setString(1,absoluteFilePath);
			prep.setLong(2,aniDBanimeId);
			prep.setString(3,ed2kHash);
			prep.setLong(4,aniDBepisodeId);
			prep.setLong(5,adbFileId);
			prep.addBatch();
			prep.executeBatch();
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	public void addFolder(MALAnime anime, java.io.File folder)
	{
		try {
			PreparedStatement prep = conn.prepareStatement("insert into folder values (?, ?);");
			prep.setInt(1,anime.getId());
			prep.setString(2,folder.getAbsolutePath()); 
			prep.addBatch();
			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public synchronized void addADBCategory(Anime e)
	{
		try {
			prep=conn.prepareStatement("insert into category values(?,?);");
			System.out.println("now da cazzzzz");
			long id = e.getAnimeId();
			List<String> cats = e.getCategoryList();
			for(String toInsert : cats)
			{
				prep.setLong(1,e.getAnimeId());
				prep.setString(2,toInsert);
				prep.addBatch();
			}
			prep.executeBatch();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public synchronized void addADBEpisode(Episode e,long fileId)
	{
		
		try {
			prep=conn.prepareStatement("insert into adbEpisodes values(? ,? ,? ,? ,? ,? ,? ,? ,? ,?);");
			prep.setLong(1,e.getAnime().getAnimeId());
			prep.setString(2,e.getEpisodeNumber());
			prep.setLong(3,fileId);
			prep.setLong(4,e.getEpisodeId());
			prep.setLong(5,e.getVotes());
			if(e.getLength()!=null)
				prep.setLong(6,e.getLength());

			prep.setString(7,e.getEnglishTitle());
			prep.setString(8,e.getRomajiTitle());
			prep.setString(9,e.getKanjiTitle());
			if(e.getAired()!=null)
				prep.setLong(10,e.getAired());
			prep.addBatch();
			
			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);
		} catch (SQLException e1) {
			System.err.println(e1.getMessage());
		}
	}
	/**
	 * Trägt das file ein und die episode
	 * @param file
	 */
	public synchronized void addADBFile(File file)
	{
		//Insert in adbfile table;
		try {
			/*
			 * 		"adbFileId LONG ," +
					"groupId LONG," +
					"myListId LONG," +
					"state INTEGER," +
					"size LONG," +
					"ed2kHash STRING," +
					"md5Hash STRING," ++
					"sha1Hash STRING," +
					"crc32Hash STRING," +
					"quality STRING," +
					"source STRING," +
					"videoCodec STRING," +
					"videoBitrate LONG," +
					"videoResolution STRING," +
					"fileType STRING," +
					"dubLanguage STRING," +
					"lengthInSecounds LONG," +
					"description STRING," +
					"airedDate LONG," +
					"aniDBFileName STRING);");
			 */
			PreparedStatement prep = conn.prepareStatement("insert into adbFiles values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			prep.setLong(1,file.getFileId());
			prep.setLong(2,file.getGroup().getGroupId());
			prep.setLong(3,file.getMyListId());
			prep.setInt(4,file.getState());
			prep.setLong(5,file.getSize());
			prep.setString(6,file.getEd2k());
			prep.setString(7,file.getMd5());
			prep.setString(8,file.getSha1());
			prep.setString(9, file.getCrc32());
			prep.setString(10,file.getQuality());
			prep.setString(11,file.getSource());
			prep.setString(12,file.getVideoCodec());
			prep.setLong(13,file.getVideoBirate());
			prep.setString(14,file.getVideoResolution());
			prep.setString(15,file.getFileType());
			prep.setString(16,file.getDubLanguage());
			prep.setLong(17,file.getLengthInSeconds());
			prep.setString(18,file.getDescription());
			prep.setLong(19,file.getAiredDate());
			prep.setString(20,file.getAniDbFileName());
			prep.executeBatch();
			/*
					"adbanimeId LONG PRIMARY KEY," +
					"episodeNumber STRING,"+
					"adbepisodeId LONG," +
					"votes LONG," +
					"length LONG" +
					"englishTitle STRING," +
					"romajiTitle STRING," +
					"kanjiTitle STRING," +
					"aired LONG);");
			 */

			
					
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public synchronized void addADBAnime(Anime anime)
	{

		try {
			PreparedStatement prepe;
			prepe= conn.prepareStatement("INSERT INTO adbAnime values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			
			prepe.setLong(1,anime.getAnimeId());
			prepe.setString(2,anime.getYear());
			prepe.setString(3,anime.getType());
			prepe.setString(4,anime.getRomajiName());
			prepe.setString(5,anime.getKanjiName());
			prepe.setString(6,anime.getEnglishName());
			prepe.setLong(7,anime.getEpisodes());
			prepe.setLong(8,anime.getNormalEpisodeCount());
			prepe.setLong(9,anime.getAirDate());
			prepe.setString(10,anime.getUrl());
			prepe.setString(11,anime.getPicname());
			prepe.setLong(12,anime.getRating());
			prepe.setLong(13,anime.getVoteCount());
			prepe.setLong(14,anime.getTempRating());
			prepe.setLong(15,anime.getTempVoteCount());
			prepe.setLong(16,anime.getAverageReviewRating());
			prepe.setLong(17,anime.getReviewCount());
			prepe.setBoolean(18,anime.get18PlusRestricted());
			prepe.setLong(19,anime.getAnimePlanetId());
			prepe.setLong(20,anime.getAnnId());
			prepe.setLong(21,anime.getAllCinemaId());
			prepe.setString(22,anime.getAnimeNfoId());
			
			prepe.addBatch();
			conn.setAutoCommit(false);
			prepe.executeBatch();
			conn.setAutoCommit(true);
			
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public ArrayList<Anime> fetchOwnADBAnime()
	{		/**
		 * CRATE TABLE IF NOT EXISTS adbAnime(" +
		"animeId LONG PRIMARY KEY," +
		"year STRING," +
		"type STRING," +
		"romanjiName STRING," +
		"kanjiName STRING," +
		"englishName STRING," +
		"episodes LONG," +
		"normalEpisodeCount LONG," +
		"airDate LONG," +
		"url STRING," +
		"picname STRING," +
		"rating LONG," +
		"voteCount LONG," +
		"tempRating LONG," +
		"tempVoteCount LONG," +
		"averageReviewRating LONG," +
		"reviewCount LONG," +
		"is18PlusRestricted BOOLEAN," +
		"animePlanetId LONG," +
		"annId LONG," +
		"allCinemaId LONG," +
		"animeNfoId STRING," +
		"dataRecordUpdate LONG," +
		"specialCount LONG," +
		"creditsCount LONG," +
		"otherCount LONG," +
		"trailerCount LONG," +
		"parodyCount);");
*/
		ArrayList<Anime> result= new ArrayList<Anime>();
		ResultSet rs;
		ResultSet catrs;
		try {
			rs=stat.executeQuery("SELECT DISTINCT * from adbAnime WHERE animeId IN(SELECT adbAnimeId from fileloc);");
			//TODO querry
			while(rs.next())
			{
				Anime anime = new Anime();
				anime.setAnimeId(rs.getLong("animeId"));
				anime.setYear(rs.getString("year"));
				anime.setType(rs.getString("type"));
				anime.setRomajiName(rs.getString("romanjiName"));
				anime.setKanjiName(rs.getString("kanjiName"));
				anime.setEnglishName(rs.getString("englishName"));
				anime.setNormalEpisodeCount(rs.getLong("normalEpisodeCount"));
				anime.setAirDate(rs.getLong("airDate"));
				anime.setUrl(rs.getString("url"));
				anime.setPicname(rs.getString("picname"));
				anime.setRating(rs.getLong("rating"));
				anime.setVoteCount(rs.getLong("voteCount"));
				anime.setTempRating(rs.getLong("tempRating"));
				anime.setTempVoteCount(rs.getLong("tempVoteCount"));
				anime.setAverageReviewRating(rs.getLong("averageReviewRating"));
				anime.setReviewCount(rs.getLong("reviewCount"));
				anime.set18PlusRestricted(rs.getBoolean("is18PlusRestricted"));
				anime.setAnimePlanetId(rs.getLong("animePlanetId"));
				anime.setAnnId(rs.getLong("annId"));
				anime.setAllCinemaId(rs.getLong("allCinemaId"));
				anime.setAnimeNfoId(rs.getString("animeNfoId"));
				anime.setDateRecordUpdated(rs.getLong("dataRecordUpdate"));
				anime.setSpecialsCount(rs.getLong("specialCount"));
				anime.setCreditsCount(rs.getLong("creditsCount"));
				anime.setTrailerCount(rs.getLong("trailercount"));
				anime.setParodyCount(rs.getLong("parodyCount"));
	
				result.add(anime);
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Anime a : result)
		{
			try {
				catrs = stat.executeQuery("SELECT category from category where animeId="+a.getAnimeId()+";");
				List<String> catlist = new ArrayList<String>();
				while(catrs.next())
				{
					catlist.add(catrs.getString("category"));
				}
				a.setCategoryList(catlist);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		
		return result;
	}
	public ArrayList<JAMEpisode> fetchEpisodes(long animeId)
	{
		ArrayList<JAMEpisode> episodes = new ArrayList<JAMEpisode>();
		ResultSet rs;
		
		try {
			rs=stat.executeQuery("SELECT * FROM adbEpisodes WHERE adbanimeId IN ( SELECT adbAnimeId FROM fileloc WHERE adbAnimeId ="+animeId+");");
			while(rs.next())
			{
				JAMEpisode episode = new JAMEpisode();
				episode.setEpisodeId(rs.getLong("epId"));
				episode.setVotes(rs.getLong("votes"));
				episode.setLength(rs.getLong("length"));
				episode.setRomajiTitle(rs.getString("romanjiTitle"));
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return episodes;
	}
	
	public ArrayList<MALAnime> fetchOwnAnime()
	{
		ArrayList<MALAnime> result = new ArrayList<MALAnime>();
		ResultSet rs;
		try {
			rs=stat.executeQuery("Select distinct * from maldump natural join folder");
			while(rs.next())
			{
				MALAnime entry = new MALAnime();
				entry.setId(rs.getInt("id"));
				entry.setTitle(rs.getString("title"));
				entry.setSynopsis(rs.getString("synopsis"));
				entry.setType(rs.getString("type"));
				entry.setRank(rs.getString("rank"));
				entry.setPopularity_rank(rs.getString("popularity_rank"));
				entry.setImage_url(rs.getString("image_url"));
				entry.setEpisodes(rs.getInt("episodes"));
				entry.setStatus(rs.getString("status"));
				entry.setStart_date(rs.getString("start_date"));
				entry.setEnd_date(rs.getString("end_date"));
				entry.setClassification(rs.getString("classification"));
				entry.setMembers_score(rs.getString("members_score"));
				entry.setMembers_count(rs.getInt("members_count"));
				entry.setFavorited_count(rs.getString("favorited_count"));
				entry.setListed_anime_id(rs.getInt("listed_anime_id"));
				entry.setWatched_episodes(rs.getInt("watched_episodes"));
				entry.setScore(rs.getString("score"));
				entry.setWatched_status(rs.getString("watched_status"));
				
				result.add(entry);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public Map<Integer,String> fetchFolderMap()
	{
		Map<Integer,String> resultMap = new HashMap<Integer,String>();
		ResultSet rs;
	
			try {
				rs = stat.executeQuery("select * from folder");
				while(rs.next())
				{
					resultMap.put(rs.getInt("id"),rs.getString("folderpath"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		return resultMap;
	}

	public void addMALAnime(MALAnime anime) {
		try {
			PreparedStatement prep = conn
					.prepareStatement("insert into maldump values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			/*
			 * 	private int id;
				private String title;
				private String synopsis;
				private String type;
				private String rank;
				private String popularity_rank;
				private String image_url;
				private int episodes;
				private String status;
				private String start_date;
				private String end_date;
				private String classification;
				private String members_score;
				private int members_count;
				private String favorited_count;
				private int listed_anime_id;
				private int watched_episodes;
				private String score;
				private String watchedStatus;

			 */
			prep.setInt(1,anime.getId());
			prep.setString(2,anime.getTitle());
			prep.setString(3,anime.getSynopsis());
			prep.setString(4,anime.getType());
			prep.setString(5,anime.getRank());
			prep.setString(6,anime.getPopularity_rank());
			prep.setString(7,anime.getImage_url());
			prep.setInt(8,anime.getEpisodes());
			prep.setString(9,anime.getStatus());
			prep.setString(10,anime.getStart_date());
			prep.setString(11,anime.getEnd_date());
			prep.setString(12,anime.getClassification());
			prep.setString(13,anime.getMembers_score());
			prep.setInt(14,anime.getMembers_count());
			prep.setString(15,anime.getFavorited_count());
			prep.setInt(16,anime.getListed_anime_id());
			prep.setInt(17,anime.getWatched_episodes());
			prep.setString(18,anime.getScore());
			prep.setString(19,anime.getWatched_status());
			
			prep.addBatch();
			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			System.err.println("Sql Exception");
		}
	}

	public ArrayList<MALAnime> fetchAllMALAnime()
	{
			ArrayList<MALAnime> resultList = new ArrayList<MALAnime>();
			ResultSet rs;
			try {
				rs = stat.executeQuery("select * from maldump;");
				while(rs.next())
				{			
					MALAnime entry = new MALAnime();
					entry.setId(rs.getInt("id"));
					entry.setTitle(rs.getString("title"));
					entry.setSynopsis(rs.getString("synopsis"));
					entry.setType(rs.getString("type"));
					entry.setRank(rs.getString("rank"));
					entry.setPopularity_rank(rs.getString("popularity_rank"));
					entry.setImage_url(rs.getString("image_url"));
					entry.setEpisodes(rs.getInt("episodes"));
					entry.setStatus(rs.getString("status"));
					entry.setStart_date(rs.getString("start_date"));
					entry.setEnd_date(rs.getString("end_date"));
					entry.setClassification(rs.getString("classification"));
					entry.setMembers_score(rs.getString("members_score"));
					entry.setMembers_count(rs.getInt("members_count"));
					entry.setFavorited_count(rs.getString("favorited_count"));
					entry.setListed_anime_id(rs.getInt("listed_anime_id"));
					entry.setWatched_episodes(rs.getInt("watched_episodes"));
					entry.setScore(rs.getString("score"));
					entry.setWatched_status(rs.getString("watched_status"));
					
					resultList.add(entry);
					
					
				}
			} catch (SQLException e) {
					System.out.println(e.getMessage());
			}

			return resultList;
		
	}
	public MALAnime fetchMALAnime(int MALid)
	{
		MALAnime entry = new MALAnime();
		ResultSet rs;
		try {
			rs = stat.executeQuery("select * from maldumpe WHERE id="+MALid+"+;");
			while(rs.next())
			{			

				entry.setId(rs.getInt("id"));
				entry.setTitle(rs.getString("title"));
				entry.setSynopsis(rs.getString("synopsis"));
				entry.setType(rs.getString("type"));
				entry.setRank(rs.getString("rank"));
				entry.setPopularity_rank(rs.getString("popularity_rank"));
				entry.setImage_url(rs.getString("image_url"));
				entry.setEpisodes(rs.getInt("episodes"));
				entry.setStatus(rs.getString("status"));
				entry.setStart_date(rs.getString("start_date"));
				entry.setEnd_date(rs.getString("end_date"));
				entry.setClassification(rs.getString("classification"));
				entry.setMembers_score(rs.getString("members_score"));
				entry.setMembers_count(rs.getInt("members_count"));
				entry.setFavorited_count(rs.getString("favorited_count"));
				entry.setListed_anime_id(rs.getInt("listed_anime_id"));
				entry.setWatched_episodes(rs.getInt("watched_episodes"));
				entry.setScore(rs.getString("score"));
				entry.setWatched_status(rs.getString("watched_status"));

				
		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return entry;
	}
	public void addMALAnimeList(ArrayList<MALAnime> inputList)
	{
		
	}

	public static DBMapper getInstance() {
		return instance;

	}
}
