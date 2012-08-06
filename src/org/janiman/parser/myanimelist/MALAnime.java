package org.janiman.parser.myanimelist;

public class MALAnime {



	private int id;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getPopularity_rank() {
		return popularity_rank;
	}
	public void setPopularity_rank(String popularity_rank) {
		this.popularity_rank = popularity_rank;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public int getEpisodes() {
		return episodes;
	}
	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getMembers_score() {
		return members_score;
	}
	public void setMembers_score(String members_score) {
		this.members_score = members_score;
	}
	public int getMembers_count() {
		return members_count;
	}
	public void setMembers_count(int members_count) {
		this.members_count = members_count;
	}
	public String getFavorited_count() {
		return favorited_count;
	}
	public void setFavorited_count(String favorited_count) {
		this.favorited_count = favorited_count;
	}
	public int getListed_anime_id() {
		return listed_anime_id;
	}
	public void setListed_anime_id(int listed_anime_id) {
		this.listed_anime_id = listed_anime_id;
	}
	public int getWatched_episodes() {
		return watched_episodes;
	}
	public void setWatched_episodes(int watched_episodes) {
		this.watched_episodes = watched_episodes;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getWatched_status() {
		return watched_status;
	}
	public void setWatched_status(String watchedStatus) {
		this.watched_status = watchedStatus;
	}
	@Override
	public int hashCode() {
		
		return id;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MALAnime other = (MALAnime) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString()
	{
		return title;
	}
	
	


}
