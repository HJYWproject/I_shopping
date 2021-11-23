package com.example.i_shopping.Post.Service;

import com.example.i_shopping.Post.Domain.FaqPostEntity;
import com.example.i_shopping.Post.Dto.FaqPostForm;
import com.example.i_shopping.Post.Repository.FaqPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FaqPostService {
    @Autowired
    private final FaqPostRepository faqpostRepository;

    @Transactional
    public Long save(FaqPostForm faqpostForm){
        return faqpostRepository.save(faqpostForm.toEntity()).getId();
    }

    @Transactional
    public void postDelete(Long id) throws Exception{
        faqpostRepository.deleteById(id);
    }

    private FaqPostForm convertEntityToDto(FaqPostEntity postEntity){
        return FaqPostForm.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .userid(postEntity.getUserid())
                .build();
    }

    @Transactional
    public List<FaqPostForm> getPostList(){
        List<FaqPostEntity> postEntities = faqpostRepository.findAll();
        List<FaqPostForm> postDtoList = new ArrayList<>();
        if(postEntities.isEmpty()) return postDtoList;
        for(FaqPostEntity faqPostEntity : postEntities){
            postDtoList.add(this.convertEntityToDto(faqPostEntity));
        }
        return postDtoList;
    }

    @Transactional
    public FaqPostForm getPost(Long id){
        FaqPostEntity faqpostEntity = faqpostRepository.findById(id).get();
        return convertEntityToDto(faqpostEntity);
    }

    @Transactional
    public Long updatePost(FaqPostForm faqpostForm){
        return faqpostRepository.save(faqpostForm.toEntity()).getId();
    }
}
