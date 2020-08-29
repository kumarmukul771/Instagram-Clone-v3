package Models;

public class UserAccountSettings {
    private String description,username,display_name,profile_photo,website;
    private long followers,following,posts;

    public UserAccountSettings(String description, String username, String display_name, String profile_photo,
                               String website, long followers, long following, long posts) {
        this.description = description;
        this.username = username;
        this.display_name = display_name;
        this.profile_photo = profile_photo;
        this.website = website;
        this.followers = followers;
        this.following = following;
        this.posts = posts;
    }

    public UserAccountSettings() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public long getFollowing() {
        return following;
    }

    public void setFollowing(long following) {
        this.following = following;
    }

    public long getPosts() {
        return posts;
    }

    public void setPosts(long posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "UserAccountSettings{" +
                "description='" + description + '\'' +
                ", username='" + username + '\'' +
                ", display_name='" + display_name + '\'' +
                ", profile_photo='" + profile_photo + '\'' +
                ", website='" + website + '\'' +
                ", followers=" + followers +
                ", following=" + following +
                ", posts=" + posts +
                '}';
    }
}
