package net.iaround.android.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import android.content.Context;

public class TextUtil {
	public static final String NEARBY_PATH = "nearby/Datas/nearby.iaround";
	public static final String MEETGAME_PATH = "meetgame/Datas/meetgame.iaround";
	public static final String FOCUS_PATH = "focus/Datas/focus.iaround";
	public static final String VIP_PRIVILEGE_PATH = "vip/Datas/vip_privilege.iaround";
	public static final String MESSAGECENTER_PATH = "messagecenter/Datas/messagecenter.iaround";
	public static final String CONTACT_CHAT_PATH="contact/Datas/chat.iaround";
	public static final String CONTACT_FRIEND_PATH="contact/Datas/friend.iaround";

	public static String getJosn(Context context, String path) {
		if (path != null) {
			try {
				InputStream is = context.getAssets().open(path);
				return readTextFile(is);
			} catch (IOException e) {
				return null;
			}
		}
		return null;
	}

	public static String readTextFile(InputStream inputStream) {
		String readedStr = "";
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String tmp;
			while ((tmp = br.readLine()) != null) {
				readedStr += tmp;
			}
			br.close();
			inputStream.close();
		} catch (UnsupportedEncodingException e) {
			return null;
		} catch (IOException e) {
			return null;
		}

		return readedStr;
	}
}
