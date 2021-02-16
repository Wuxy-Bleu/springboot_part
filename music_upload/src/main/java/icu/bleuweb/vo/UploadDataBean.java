package icu.bleuweb.vo;

import lombok.Data;

@Data
public class UploadDataBean {

    private String songName;
    private String singerName;
    private String albumName;
    //TODO 我用String 来存储时长 java中是否有更好的方案，应该不是java.util.Date吧，那是时刻
    //同时 在mysql中存储这些类型该怎么办
    private String duration;
    //TODO Java中有什么存储url的更好的方案吗
    private String albumCoverUrl;
    private String songUrl;

}
