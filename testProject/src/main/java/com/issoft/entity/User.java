package com.issoft.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: nikitadavydov
 * Date: 9/17/12
 */
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "userid")
    private String user_id;
    @Column(name = "userpassword")
    private String user_password;
    @Column(name = "homedirectory")
    private String home_directory;
    @Column(name = "enableflag")
    private boolean enableflag;
    @Column(name = "writepermission")
    private boolean write_permission;
    @Column(name = "idletime")
    private int idle_time;
    @Column(name = "uploadrate")
    private int upload_rate;
    @Column(name = "downloadrate")
    private int download_rate;
    @Column(name = "maxloginnumber")
    private int max_login_number;
    @Column(name = "maxloginperip")
    private int max_login_per_ip;
    @Column(name = "email")
    private String email;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole user_roles = new UserRole();


    //getters & setters

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private void resetUserId() {
        this.user_id = null;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = md5(user_password);
    }

    private void resetUser_password() {
        this.user_password = null;
    }

    public String getHome_directory() {
        return home_directory;
    }

    public void setHome_directory(String home_directory) {
        this.home_directory = home_directory;
    }

    private void resetHome_directory() {
        this.home_directory = null;
    }

    public boolean isEnableflag() {
        return enableflag;
    }

    public void setEnableflag(boolean enableflag) {
        this.enableflag = enableflag;
    }

    public void resetEnableflag() {
        this.enableflag = true;
    }

    public boolean isWrite_permission() {
        return write_permission;
    }

    public void setWrite_permission(boolean write_permission) {
        this.write_permission = write_permission;
    }

    public void resetWritepermission() {
        this.write_permission = true;
    }

    public int getIdle_time() {
        return idle_time;
    }

    public void setIdle_time(int idle_time) {
        this.idle_time = idle_time;
    }

    public void resetIdle_time() {
        this.idle_time = 0;
    }

    public int getUpload_rate() {
        return upload_rate;
    }

    public void setUpload_rate(int upload_rate) {
        this.upload_rate = upload_rate;
    }

    public void resetUpload_rate() {
        this.upload_rate = 0;
    }

    public int getDownload_rate() {
        return download_rate;
    }

    public void setDownload_rate(int download_rate) {
        this.download_rate = download_rate;
    }

    public void resetDownload_rate() {
        this.download_rate = 0;
    }

    public int getMax_login_number() {
        return max_login_number;
    }

    public void setMax_login_number(int max_login_number) {
        this.max_login_number = max_login_number;
    }

    public void resetMax_login_number() {
        this.max_login_number = 0;
    }

    public int getMax_login_per_ip() {
        return max_login_per_ip;
    }

    public void setMax_login_per_ip(int max_login_per_ip) {
        this.max_login_per_ip = max_login_per_ip;
    }

    public void resetMax_login_per_ip() {
        this.max_login_per_ip = 0;
    }

    public UserRole getUser_roles() {
        return user_roles;
    }

    public void setUser_roles(UserRole user_roles) {
        this.user_roles = user_roles;
    }

    public void resetUser_roles() {
        this.user_roles = null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void resetEmail() {
        this.email = null;
    }


    public void resetUser() {
        resetUserId();
        resetUser_password();
        resetHome_directory();
        resetEnableflag();
        resetWritepermission();
        resetIdle_time();
        resetUpload_rate();
        resetDownload_rate();
        resetMax_login_number();
        resetMax_login_per_ip();
        resetEmail();
        resetUser_roles();
    }

    private String md5(String source) {
        try {
            MessageDigest mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(source.getBytes(), 0, source.length());
            source = new BigInteger(1, mdEnc.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
        }
        return source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (download_rate != user.download_rate) return false;
        if (enableflag != user.enableflag) return false;
        if (idle_time != user.idle_time) return false;
        if (max_login_per_ip != user.max_login_per_ip) return false;
        if (max_login_number != user.max_login_number) return false;
        if (upload_rate != user.upload_rate) return false;
        if (write_permission != user.write_permission) return false;
        if (home_directory != null ? !home_directory.equals(user.home_directory) : user.home_directory != null)
            return false;
        if (user_id != null ? !user_id.equals(user.user_id) : user.user_id != null) return false;
        if (user_password != null ? !user_password.equals(user.user_password) : user.user_password != null)
            return false;
        if (user_roles != null ? !user_roles.equals(user.user_roles) : user.user_roles != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user_id != null ? user_id.hashCode() : 0;
        result = 31 * result + (user_password != null ? user_password.hashCode() : 0);
        result = 31 * result + (home_directory != null ? home_directory.hashCode() : 0);
        result = 31 * result + (enableflag ? 1 : 0);
        result = 31 * result + (write_permission ? 1 : 0);
        result = 31 * result + idle_time;
        result = 31 * result + upload_rate;
        result = 31 * result + download_rate;
        result = 31 * result + max_login_number;
        result = 31 * result + max_login_per_ip;
        result = 31 * result + (user_roles != null ? user_roles.hashCode() : 0);
        return result;
    }
}
