package icu.bleuweb.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
//@NoArgsConstructor
public class MusicBean implements Serializable {

    private Integer id;
    private String songName;
    private String singerName;
    private String albumName;
    private String coverURL;
    private String albumDesc;

}
