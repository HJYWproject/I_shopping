package com.example.i_shopping.Post.Service;

import com.example.i_shopping.Post.Domain.FreePostEntity;
import com.example.i_shopping.Post.Dto.FreePostForm;
import com.example.i_shopping.Post.Repository.FreePostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FreePostService {
    @Autowired
    private final FreePostRepository freepostRepository;

    @Transactional
    public Long save(FreePostForm freepostForm){
        return freepostRepository.save(freepostForm.toEntity()).getId();
    }

    @Transactional
    public void postDelete(Long id) throws Exception{
        freepostRepository.deleteById(id);
    }

    private FreePostForm convertEntityToDto(FreePostEntity postEntity){
        return FreePostForm.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .userid(postEntity.getUserid())
                .build();
    }

    @Transactional
    public List<FreePostForm> getPostList(){
        List<FreePostEntity> postEntities = freepostRepository.findAll();
        List<FreePostForm> postDtoList = new ArrayList<>();
        if(postEntities.isEmpty()) return postDtoList;
        for(FreePostEntity freePostEntity : postEntities){
            postDtoList.add(this.convertEntityToDto(freePostEntity));
        }
        return postDtoList;
    }

    @Transactional
    public FreePostForm getPost(Long id){
        FreePostEntity freepostEntity = freepostRepository.findById(id).get();
        return convertEntityToDto(freepostEntity);
    }

    @Transactional
    public Long updatePost(FreePostForm freepostForm){
        return freepostRepository.save(freepostForm.toEntity()).getId();
    }
}
