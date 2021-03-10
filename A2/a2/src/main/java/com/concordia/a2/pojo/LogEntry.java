/*package com.concordia.a2.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LogEntry implements Comparable{
    @XmlElement(required = true)
    private String isrc;
    @XmlElement(required = true)
    private String timeStamp;
    @XmlElement(required = true)
    private String type;

    public LogEntry(String isrc, String type) {
        this.isrc = isrc;
        this.type = type;
        this.timeStamp = getPostTime();
    }

    public LogEntry(){}

    public String getisrc() {
        return isrc;
    }

    public void setisrc(String isrc) {
        this.isrc = isrc;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "isrc='" + isrc + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getPostTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = formatter.format(now);
        return date;
    }

    @Override
    public int compareTo(Object o) {
        return this.timeStamp.compareTo(((LogEntry)o).getTimeStamp());
    }
}*/
