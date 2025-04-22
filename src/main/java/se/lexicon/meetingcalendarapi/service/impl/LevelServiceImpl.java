package se.lexicon.meetingcalendarapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.meetingcalendarapi.converter.Level.LevelConverter;
import se.lexicon.meetingcalendarapi.domain.dto.Level.LevelDTOForm;
import se.lexicon.meetingcalendarapi.domain.dto.Level.LevelDTOView;
import se.lexicon.meetingcalendarapi.domain.entity.Level;
import se.lexicon.meetingcalendarapi.exception.DataDuplicateException;
import se.lexicon.meetingcalendarapi.repository.LevelRepository;
import se.lexicon.meetingcalendarapi.service.LevelService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;
    private final LevelConverter levelConverter;

    @Autowired
    public LevelServiceImpl(final LevelRepository levelRepository, final LevelConverter levelConverter) {
        this.levelRepository = levelRepository;
        this.levelConverter = levelConverter;
    }

    @Override
    public List<LevelDTOView> getAllLevels() {
        return levelRepository.findAll().stream()
                .map(levelConverter::toView).collect(Collectors.toList());
    }

    @Override
    public Optional<LevelDTOView> findLevelById(Long id) {
        return levelRepository.findById(id).map(levelConverter::toView);
    }

    @Override
    public boolean existLevelById(Long id) {
        return levelRepository.existsById(id);
    }

    @Override
    public LevelDTOView createLevel(LevelDTOForm form) {
        if (form == null) throw new IllegalArgumentException("Level form can't be null");
        if (levelRepository.existsByName(form.name().toLowerCase())) throw new DataDuplicateException("Level already exists");

        Level newLevel = levelRepository.save(levelConverter.toEntity(form));

        return levelConverter.toView(newLevel);
    }

    @Override
    public void deleteLevel(Long id) {
        levelRepository.deleteById(id);
    }
}
