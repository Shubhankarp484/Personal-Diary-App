package net.engineeringdigest.diaryApp.controller;


import net.engineeringdigest.diaryApp.entity.DiaryEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class DiaryEntryControllerV2 {



    @GetMapping
    public List<DiaryEntry> getAll(){
    return null;
    }

    @PostMapping
    public boolean createEntry(@RequestBody DiaryEntry myEntry){
      return true;
    }

    @GetMapping("id/{myId}")
    public DiaryEntry getDiaryEntryById(@PathVariable Long myId){
        return null;
    }

    @DeleteMapping("id/{myId}")
    public DiaryEntry deleteDiaryEntryById(@PathVariable Long myId){
        return null;
    }

    @PutMapping("/id/{myId}")
    public DiaryEntry updateDiaryById(@PathVariable Long myId, @RequestBody DiaryEntry myEntry){
        return null;
    }

}