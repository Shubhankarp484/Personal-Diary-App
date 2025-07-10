package net.engineeringdigest.diaryApp.controller;


import net.engineeringdigest.diaryApp.entity.DiaryEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class DiaryEntryController {

    private Map<Long, DiaryEntry> diaryEntries = new HashMap<>();

    @GetMapping
    public List<DiaryEntry> getAll(){
       return new ArrayList<>(diaryEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody DiaryEntry myEntry){
       diaryEntries.put(myEntry.getId(), myEntry);
       return true;
    }

    @GetMapping("id/{myId}")
    public DiaryEntry getDiaryEntryById(@PathVariable Long myId){
       return diaryEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public DiaryEntry deleteDiaryEntryById(@PathVariable Long myId){
        return diaryEntries.remove(myId);
    }

    @PutMapping("/id/{myId}")
    public DiaryEntry updateDiaryById(@PathVariable Long myId, @RequestBody DiaryEntry myEntry){
        return diaryEntries.put(myId, myEntry);
    }

}
