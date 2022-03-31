package org.zerock.partFive.security.service;

import org.zerock.partFive.entity.ClubMember;
import org.zerock.partFive.entity.Note;
import org.zerock.partFive.security.dto.NoteDTO;

import java.util.List;

public interface NoteService {

    Long register(NoteDTO dto);

    NoteDTO get(Long num);

    void modify(NoteDTO dto);

    void remove(Long num);

    List<NoteDTO>getAllWithWriter(String writerEmail);

    default Note dtoToEntity(NoteDTO dto){
        Note note = Note.builder()
                .num(dto.getNum())
                .title(dto.getTitle())
                .content(dto.getContent())
                .clubMember(ClubMember.builder().email(dto.getWriterEmail()).build())
                .build();

        return note;
    }

    default NoteDTO entityToDto(Note entity){
        NoteDTO dto = NoteDTO.builder()
                .num(entity.getNum())
                .title(entity.getTitle())
                .content(entity.getContent())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .writerEmail(entity.getClubMember().getEmail())
                .build();

        return dto;
    }
}
