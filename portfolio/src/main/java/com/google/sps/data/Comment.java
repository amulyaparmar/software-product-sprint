package com.google.sps.data;

import java.util.Date;

/** Class containing comment and comment identification information */
public final class Comment {
  private final Date postTime;
  private final String name;
  private final String comment;

  public Comment(Date postTime, String name, String comment) {
    this.postTime = postTime;
    this.name = name;
    this.comment = comment;
  }

  public Date getPostTime() {
    return postTime;
  }

  public String getName() {
    return name;
  }

  public String getComment() {
    return comment;
  }
  
}