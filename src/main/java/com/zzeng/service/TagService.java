package com.zzeng.service;

import com.zzeng.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by DERK on 2023/4/16.
 */
public interface TagService {

    Tag saveTag(Tag type);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTagTop(Integer size);

    List<Tag> listTag(String ids);

    Tag updateTag(Long id, Tag type);

    void deleteTag(Long id);
}
