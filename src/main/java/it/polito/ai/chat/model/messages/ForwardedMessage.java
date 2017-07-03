package it.polito.ai.chat.model.messages;

import java.util.Date;

public class ForwardedMessage implements Comparable<ForwardedMessage> {

	/*
	 * this class represents what is sent and showed to the user on the view
	 * it is created adding the needed information to the object ReceivedMessage sent by the user on the web socket
	 */

    private String nickname;
    private String avatar;
    private Date timestamp;
    private String content;

	public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(ForwardedMessage o) {
        Date thisTimestamp = this.getTimestamp();
        Date thatTimestamp = o.getTimestamp();
        return thisTimestamp.compareTo(thatTimestamp);
    }

}
