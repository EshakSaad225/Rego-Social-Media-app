package com.penta.penta_service_posts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.penta.penta_service_posts.domain.Post;
import com.penta.penta_service_posts.domain.Users;
import com.penta.penta_service_posts.model.PostDTO;
import com.penta.penta_service_posts.repos.PostRepository;
import com.penta.penta_service_posts.repos.UsersRepository;
import com.penta.penta_service_posts.util.NotFoundException;

@Service
public class PostService {

    @Autowired
    private final PostRepository postRepository ;
    
    @Autowired
    private UsersRepository usersRepository ;

    public List<Users> getMentionsFromText(String text){ 

      String regex = "\\$%<(.*?)>\\$\\$<(.*?)>";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(text);
      List<Users> usres = new ArrayList <> () ;

      while (matcher.find()) {
        System.out.println("loop");
          try {
              Optional <Users> user = usersRepository.findById(UUID.fromString(matcher.group(1))) ;
              if(user.isPresent() && user.get().getName().equals(  matcher.group(2) )){
                usres.add(user.get()) ;
              }
          } catch (Exception e) {
            System.out.println("Exception => "+e);
            throw e ;
          }
      }
      System.out.println("usersss => "+usres);
    return usres ;
  }

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDTO> findAll() {
        final List<Post> posts = postRepository.findAll(Sort.by("id"));
        return posts.stream()
                .map(post -> mapToDTO(post, new PostDTO()))
                .toList();
    }

    public PostDTO get(final UUID id) {
        return postRepository.findById(id)
                .map(post -> mapToDTO(post, new PostDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final PostDTO postDTO) {
        final Post post = new Post();
        mapToEntity(postDTO, post);
        

        return postRepository.save(post).getId();
    }

    public void update(final UUID id, final PostDTO postDTO) {
        final Post post = postRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(postDTO, post);
        postRepository.save(post);
    }

    public void delete(final UUID id) {
        postRepository.deleteById(id);
    }

    private PostDTO mapToDTO(final Post post, final PostDTO postDTO) {
        postDTO.setId(post.getId());
        postDTO.setType(post.getType());
        postDTO.setText(post.getText());
        postDTO.setAttachments(post.getAttachments());
        postDTO.setIsSaved(post.getIsSaved());
        postDTO.setIsShared(post.getIsShared());
        postDTO.setSharedPost(post.getSharedPost());
        postDTO.setCreatedBy(post.getCreatedBy());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setUpdatedAt(post.getUpdatedAt());
        postDTO.setMoreData(post.getMoreData());
        postDTO.setMentions(post.getMentions());
        postDTO.setHashtags(post.getHashtags());
        return postDTO;
    }

    private Post mapToEntity(final PostDTO postDTO, final Post post) {

        post.setType(postDTO.getType());
        post.setText(postDTO.getText());
        post.setAttachments(postDTO.getAttachments());
        post.setIsSaved(postDTO.getIsSaved());
        post.setIsShared(postDTO.getIsShared());
        post.setSharedPost(postDTO.getSharedPost());
        post.setCreatedBy(postDTO.getCreatedBy());
        post.setCreatedAt(postDTO.getCreatedAt());
        post.setUpdatedAt(postDTO.getUpdatedAt());
        post.setMoreData(postDTO.getMoreData());
        post.setMentions(getMentionsFromText( postDTO.getText()));
        post.setHashtags(postDTO.getHashtags());
        return post;
    }

}
