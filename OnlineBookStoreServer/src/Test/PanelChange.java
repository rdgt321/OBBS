package Test;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import DBC.ConnectionFactory;
import Server.Const;

public class PanelChange extends JFrame {

	public static void main(String[] args) {
		Const.loadConfig();
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into promotion(leastintegral,name,startdate,enddate,discountrate,equivalentdenomination,bonduselimit) values (?,?,?,?,?,?,?)";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1,0);
			ps.setString(2,"test");
			ps.setDate(3, new java.sql.Date(Calendar.getInstance()
					.getTimeInMillis()));
			ps.setDate(4, new java.sql.Date(Calendar.getInstance()
					.getTimeInMillis()));
			ps.setDouble(5, 0.1);
			ps.setDouble(6,10);
			ps.setDouble(7, 100);
			row = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(row);

	}

}