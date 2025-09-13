package net.shubhankarpotnis.diaryApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "config_diary_app")
@Data
@NoArgsConstructor
public class ConfigDiaryAppEntity {
    private String key;
    private String value;

}
