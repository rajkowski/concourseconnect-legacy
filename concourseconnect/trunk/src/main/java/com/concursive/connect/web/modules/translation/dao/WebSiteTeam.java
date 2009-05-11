/*
 * ConcourseConnect
 * Copyright 2009 Concursive Corporation
 * http://www.concursive.com
 *
 * This file is part of ConcourseConnect, an open source social business
 * software and community platform.
 *
 * Concursive ConcourseConnect is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, version 3 of the License.
 *
 * Under the terms of the GNU Affero General Public License you must release the
 * complete source code for any application that uses any part of ConcourseConnect
 * (system header files and libraries used by the operating system are excluded).
 * These terms must be included in any work that has ConcourseConnect components.
 * If you are developing and distributing open source applications under the
 * GNU Affero General Public License, then you are free to use ConcourseConnect
 * under the GNU Affero General Public License.
 *
 * If you are deploying a web site in which users interact with any portion of
 * ConcourseConnect over a network, the complete source code changes must be made
 * available.  For example, include a link to the source archive directly from
 * your web site.
 *
 * For OEMs, ISVs, SIs and VARs who distribute ConcourseConnect with their
 * products, and do not license and distribute their source code under the GNU
 * Affero General Public License, Concursive provides a flexible commercial
 * license.
 *
 * To anyone in doubt, we recommend the commercial license. Our commercial license
 * is competitively priced and will eliminate any confusion about how
 * ConcourseConnect can be used and distributed.
 *
 * ConcourseConnect is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ConcourseConnect.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Attribution Notice: ConcourseConnect is an Original Work of software created
 * by Concursive Corporation
 */

package com.concursive.connect.web.modules.translation.dao;

import com.concursive.commons.db.DatabaseUtils;
import com.concursive.commons.web.mvc.beans.GenericBean;

import java.sql.*;

/**
 * Represents the specific instance of a language team member
 *
 * @author matt
 * @created January 29, 2008
 */
public class WebSiteTeam extends GenericBean {
  private int id = -1;
  private int memberId = -1;
  private int languageId = -1;
  private Timestamp entered = null;

  public int getId() {
    return id;
  }

  public void setId(int tmp) {
    this.id = tmp;
  }

  public void setId(String tmp) {
    this.id = Integer.parseInt(tmp);
  }

  public int getMemberId() {
    return memberId;
  }

  public void setMemberId(int tmp) {
    this.memberId = tmp;
  }

  public void setMemberId(String tmp) {
    this.memberId = Integer.parseInt(tmp);
  }

  public int getLanguageId() {
    return languageId;
  }

  public void setLanguageId(int languageId) {
    this.languageId = languageId;
  }

  public void setLanguageId(String tmp) {
    this.languageId = Integer.parseInt(tmp);
  }

  public Timestamp getEntered() {
    return entered;
  }

  public void setEntered(Timestamp entered) {
    this.entered = entered;
  }

  /**
   * Constructor for the WebSiteTeam object
   */
  public WebSiteTeam() {
  }


  /**
   * Constructor for the WebSiteTeam object
   *
   * @param rs Description of the Parameter
   * @throws java.sql.SQLException Description of the Exception
   */
  public WebSiteTeam(ResultSet rs) throws SQLException {
    buildRecord(rs);
  }


  /**
   * Constructor for the WebSiteTeam object
   *
   * @param db Description of the Parameter
   * @param id Description of the Parameter
   * @throws java.sql.SQLException Description of the Exception
   */
  public WebSiteTeam(Connection db, int id) throws SQLException {
    if (id == -1) {
      throw new SQLException("Invalid id specified");
    }
    PreparedStatement pst = db.prepareStatement(
        "SELECT lt.* " +
            "FROM project_language_team lt " +
            "WHERE lt.id = ? ");
    pst.setInt(1, id);
    ResultSet rs = pst.executeQuery();
    if (rs.next()) {
      buildRecord(rs);
    }
    rs.close();
    pst.close();
  }


  /**
   * Description of the Method
   *
   * @param rs Description of the Parameter
   * @throws java.sql.SQLException Description of the Exception
   */
  protected void buildRecord(ResultSet rs) throws SQLException {
    id = rs.getInt("id");
    memberId = rs.getInt("member_id");
    languageId = rs.getInt("language_id");
    entered = rs.getTimestamp("entered");
  }


  /**
   * Description of the Method
   *
   * @param db Description of the Parameter
   * @return Description of the Return Value
   * @throws java.sql.SQLException Description of the Exception
   */
  public boolean insert(Connection db) throws SQLException {
    StringBuffer sql = new StringBuffer();
    sql.append("INSERT INTO project_language_team (member_id, language_id) ");
    sql.append("VALUES (?, ?) ");
    int i = 0;
    PreparedStatement pst = db.prepareStatement(sql.toString());
    pst.setInt(++i, memberId);
    pst.setInt(++i, languageId);
    pst.execute();
    pst.close();
    id = DatabaseUtils.getCurrVal(db, "project_language_team_id_seq", -1);
    return true;
  }

  public void delete(Connection db) throws SQLException {
    StringBuffer sql = new StringBuffer();
    sql.append("DELETE FROM project_language_team ");
    sql.append("WHERE member_id = ? AND language_id = ? ");
    int i = 0;
    PreparedStatement pst = db.prepareStatement(sql.toString());
    pst.setInt(++i, memberId);
    pst.setInt(++i, languageId);
    pst.execute();
    pst.close();
  }


}