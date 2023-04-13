/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.DbProc;

import static dao.generic.HibernateUtil.getSessionFactory;
import globalUtilities.Util;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author gaurav.khanzode
 */
public class ProcLhsTdsPanUpdtNew {

    long proc_out_parameter;
    Util utl;

    public ProcLhsTdsPanUpdtNew() {
        utl = new Util();
    }

    public long executeProcedure(final String entity_code,
            final String client_code,
            final String acc_year,
            final String assement_acc_year,
            final String quarter_no,
            final Date from_date,
            final Date to_date,
            final String tds_type_code,
            final Long client_login_session_seqno,
            final String process_type,
            final String user_code,
            final Long process_seqno) {

        Session session = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    utl.generateLog("*****Proc_Lhs_Tds_PanUpdt_New parameters******", "");
                    utl.generateLog("entity_code---", entity_code);
                    utl.generateLog("client_code---", client_code);
                    utl.generateLog("acc_year---", acc_year);
                    utl.generateLog("assement_acc_year---", assement_acc_year);
                    utl.generateLog("quarter_no---", quarter_no);
                    utl.generateLog("from_date---", from_date);
                    utl.generateLog("to_date---", to_date);
                    utl.generateLog("tds_type_code---", tds_type_code);
                    utl.generateLog("client_login_session_seqno---", client_login_session_seqno);
                    utl.generateLog("process_type---", process_type);
                    utl.generateLog("user_code---", user_code);
                    utl.generateLog("process_Seqno---", process_seqno);

                    CallableStatement cs;
                    String proc = "{call lhs_tds_panupdt_new(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                    cs = cnctn.prepareCall(proc);

                    cs.setString(1, entity_code);
                    cs.setString(2, client_code);
                    cs.setString(3, acc_year);
                    cs.setString(4, assement_acc_year);
                    cs.setString(5, quarter_no);

                    java.sql.Date sql_from_date = new java.sql.Date(from_date.getTime());
                    cs.setDate(6, sql_from_date);

                    java.sql.Date sql_to_date = new java.sql.Date(to_date.getTime());
                    cs.setDate(7, sql_to_date);

                    cs.setString(8, tds_type_code);
                    cs.setLong(9, client_login_session_seqno);
                    cs.setString(10, process_type);
                    cs.registerOutParameter(11, java.sql.Types.INTEGER);
                    cs.registerOutParameter(12, java.sql.Types.INTEGER);
                    cs.setString(13, user_code);

                    if (process_seqno != null) {
                        cs.setLong(14, process_seqno);
                    } else {
                        cs.setNull(14, java.sql.Types.INTEGER);
                    }
                    cs.executeUpdate();
                    proc_out_parameter = cs.getLong(12);
                    utl.generateLog("Proc_delete_tran_load procedure called....", "");
                }
            };
            session.beginTransaction();
            session.doWork(work);
            session.getTransaction().commit();
        } catch (Exception e) {
            proc_out_parameter = 0;
            e.printStackTrace();
        } finally {
            session.close();
        }

        return proc_out_parameter;
    }//end 
}//end
