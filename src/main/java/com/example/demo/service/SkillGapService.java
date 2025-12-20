package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.SkillGapRecord;

public interface SkillGapService {

    List<SkillGapRecord> computeGaps(Long studentProfileId);

    List<SkillGapRecord> getGapsByStudent(Long studentProfileId);
}
