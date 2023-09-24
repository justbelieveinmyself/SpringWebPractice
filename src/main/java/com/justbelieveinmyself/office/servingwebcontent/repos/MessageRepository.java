package com.justbelieveinmyself.office.servingwebcontent.repos;

import com.justbelieveinmyself.office.servingwebcontent.domain.Message;
import com.justbelieveinmyself.office.servingwebcontent.domain.User;
import com.justbelieveinmyself.office.servingwebcontent.domain.dto.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    @Query("select new com.justbelieveinmyself.office.servingwebcontent.domain.dto.MessageDto(m, count(ml), sum(case when ml = :user then 1 else 0 end) > 0) from Message m left join m.likes ml where m.tag = :tag group by m")
    Page<MessageDto> findByTag(@Param("user") User user,@Param("tag")String tag, Pageable pageable);
    @Query("select new com.justbelieveinmyself.office.servingwebcontent.domain.dto.MessageDto(m, count(ml), sum(case when ml = :user then 1 else 0 end) > 0) from Message m left join m.likes ml group by m")
    Page<MessageDto> findAll(@Param("user") User user, Pageable pageable);
    @Query("select new com.justbelieveinmyself.office.servingwebcontent.domain.dto.MessageDto(m, count(ml), sum(case when ml = :user then 1 else 0 end) > 0) from Message m left join m.likes ml where m.author = :author group by m")
    Page<MessageDto> findByAuthor(@Param("user") User user, @Param("author") User author, Pageable pageable);
}
