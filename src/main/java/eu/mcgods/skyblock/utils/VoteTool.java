package eu.mcgods.skyblock.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Scanner;

public class VoteTool {

	// 0 = Not Voted, 1 = Voted and claimed, 2 = Already claimed the vote, 3 = error on claim
	@SuppressWarnings("resource")
	public Integer makeAVote(String apiKey, String username) {
		
		int checkResult = 0;
		int claimResult = 0;
		
		try {
			URLConnection con = new URL("https://minecraft-server.eu/api/v1/?object=votes&element=claim&key=" + apiKey + "&username=" + username).openConnection();
			con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			con.connect();
			
			BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
			checkResult = new Scanner(r).nextInt();
		} catch (IOException ioException) {
			ioException.printStackTrace();
			return 3;
		}
		
		if (checkResult == 0) {
			return 0;
		} else if (checkResult == 1) {
			
			try {
				URLConnection con = new URL("https://minecraft-server.eu/api/v1/?action=post&object=votes&element=claim&key=" + apiKey + "&username=" + username).openConnection();
				con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
				con.connect();
				
				BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
				claimResult = new Scanner(r).nextInt();
			} catch (IOException ioException) {
				ioException.printStackTrace();
				return 3;
			}
			
			if(claimResult == 1) {
				return 1;
			} else {
				return 3;
			}
		} else if(checkResult == 2) {
			return 2;
		}
		return 0;
	}
}