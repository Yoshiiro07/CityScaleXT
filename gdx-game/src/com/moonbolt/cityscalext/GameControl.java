package com.moonbolt.cityscalext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;


public class GameControl {

	//  [ SECTIONS ] //
	// Account Settings    (ACC)
	// Online Transfer    (ONT)
	
	
	
	
	
	
	//Variables 
	private Json json;
	private FileHandle file;
	private Player activePlayer;
	private Thread thrOnline;
	private int threahCount = 0;
	
	public GameControl() {
		json = new Json();
		activePlayer = new Player();
	}
	
	
	
	// [ACC] //
	public void CreateNewAccount() {
		int count = 0;
		Random randnumber = new Random();
		FileHandle file = Gdx.files.local("SaveData/SvDT.json");
		
		activePlayer = new Player();
		while(count <= 100000){
			count = randnumber.nextInt(999999);
		}
		if (!file.exists()) {
			activePlayer.accountID = String.valueOf(count);
			activePlayer.name = "none";
			
			file.writeString(Base64Coder.encodeString(json.prettyPrint(activePlayer)),false);
		}
	}
	
	public void SaveAccount() {
		file = Gdx.files.local("SaveData/SvDT.json");
		file.writeString(Base64Coder.encodeString(json.prettyPrint(activePlayer)),false);
	}
	
	public void LoadingAccount() {
		FileHandle file = Gdx.files.local("SaveData/SvDT.json");
		activePlayer = json.fromJson(Player.class,Base64Coder.decodeString(file.readString()));
	}
	
	public void CreateNewCharacter() {
		
	}
	
	
	// [ACC END] //
	
	
	
	
	// [ONT] //
	private void ThreadsSyncStart() {
		thrOnline = new Thread(t1);
		thrOnline.start();
	}
	
	private Runnable t1 = new Runnable() {
		public void run() {
			try{    
				while(threahCount == 1) {
					OnlineOperation("Sync", "");            	
				}
			}
			catch(Exception ex) {
				Thread.currentThread().interrupt();	
			}	
		}
	};
	
	public void OnlineOperation(String typeOperation, String subdata) {
		
		try {
			String returnFromServer = "";
			String line = "";
			
			String data = URLEncoder.encode("ldata", "UTF-8") + "=" + URLEncoder.encode(activePlayer.accountID, "UTF-8");
	        data += "&" + URLEncoder.encode("lrequest", "UTF-8") + "=" + URLEncoder.encode("Chat", "UTF-8");
	        data += "&" + URLEncoder.encode("lservername", "UTF-8") + "=" + URLEncoder.encode("citybase.mysql.uhserver.com", "UTF-8");
	        data += "&" + URLEncoder.encode("lusername", "UTF-8") + "=" + URLEncoder.encode("citymaster", "UTF-8");
	        data += "&" + URLEncoder.encode("lpassword", "UTF-8") + "=" + URLEncoder.encode("City@2020", "UTF-8");
	        data += "&" + URLEncoder.encode("ldbname", "UTF-8") + "=" + URLEncoder.encode("citybase", "UTF-8");
	        data += "&" + URLEncoder.encode("lversion", "UTF-8") + "=" + URLEncoder.encode("a1", "UTF-8");
	        data += "&" + URLEncoder.encode("lname", "UTF-8") + "=" + URLEncoder.encode(activePlayer.name, "UTF-8");
	        data += "&" + URLEncoder.encode("lchat", "UTF-8") + "=" + URLEncoder.encode(subdata, "UTF-8");
	        
	     // Send data
	        URL url = new URL("http://moonbolt.online/Conector/Online.php");
	        URLConnection conn = url.openConnection();
	        conn.setDoOutput(true);
	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        wr.write(data);
	        wr.flush();
	        
	        // Get the response
	        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        line = "";
	        returnFromServer = "";
	        while ((line = rd.readLine()) != null) {
	        	returnFromServer = line;   
	        	//Resultado:  	
			}
	        
	        wr.close();
	        rd.close();
	        return;
			}
			
			catch(Exception ex) {	
		}
	}
	
	
	// [ONT END] //
	
}
