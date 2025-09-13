package net.shubhankarpotnis.diaryApp.repository;


import net.shubhankarpotnis.diaryApp.entity.ConfigDiaryAppEntity;
import net.shubhankarpotnis.diaryApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigDiaryAppRepository extends MongoRepository<ConfigDiaryAppEntity, ObjectId> {

}
