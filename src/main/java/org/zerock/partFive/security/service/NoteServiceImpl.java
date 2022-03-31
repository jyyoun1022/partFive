package org.zerock.partFive.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.partFive.entity.Note;
import org.zerock.partFive.repository.NoteRepository;
import org.zerock.partFive.security.dto.NoteDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{

    private final NoteRepository repository;

    @Override
    public Long register(NoteDTO dto) {
        Note note = dtoToEntity(dto);
        log.info("=========================");
        log.info(note);

        repository.save(note);
        return note.getNum();
    }

    @Override
    public NoteDTO get(Long num) {
        Optional<Note> result = repository.getWithClubMember(num);
        if(result.isPresent()){
            Note note = result.get();
            entityToDto(note);
        }
        return null;
    }

    @Override
    public void modify(NoteDTO dto) {
        Long num = dto.getNum();
        Optional<Note> result = repository.findById(num);
        if(result.isPresent()){
            Note note = result.get();
            note.changeTitle(dto.getTitle());
            note.changeContent(dto.getContent());

            repository.save(note);
        }
    }

    @Override
    public void remove(Long num) {
        repository.deleteById(num);
    }

    @Override
    public List<NoteDTO> getAllWithWriter(String writerEmail) {
        List<Note> result = repository.getList(writerEmail);

        return result.stream().map(note -> entityToDto(note)).collect(Collectors.toList());
    }
}
