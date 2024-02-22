package com.donghwa.models.member;

import com.donghwa.api.members.validator.JoinValidator;
import lombok.RequiredArgsConstructor;
import com.donghwa.api.members.dto.RequestJoin;
import com.donghwa.commons.constants.MemberType;
import com.donghwa.repositories.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
public class MemberJoinService {
    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JoinValidator validator;

    public void save(RequestJoin join, Errors errors) {
        validator.validate(join, errors);
        if (errors.hasErrors()) {
            return;
        }

        save(join);
    }

    public void save(RequestJoin join) {
        String password = passwordEncoder.encode(join.password());
        Member member = Member.builder()
                .email(join.email())
                .password(password)
                .name(join.name())
                .mobile(join.mobile())
                .type(MemberType.USER)
                .build();
        save(member);
    }

    public void save(Member member) {
        System.out.println(member);
        repository.saveAndFlush(member);
    }
}
