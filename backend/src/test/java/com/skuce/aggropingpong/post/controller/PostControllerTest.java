package com.skuce.aggropingpong.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skuce.aggropingpong.post.dto.PostCreateRequestDto;
import com.skuce.aggropingpong.post.dto.PostDetailResponseDto;
import com.skuce.aggropingpong.post.dto.PostSimpleResponseDto;
import com.skuce.aggropingpong.post.dto.PostUpdateRequestDto;
import com.skuce.aggropingpong.post.service.PostReadService;
import com.skuce.aggropingpong.post.service.PostWriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(controllers = PostController.class)
class PostControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private PostReadService postReadService;
    @MockitoBean
    private PostWriteService postWriteService;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("단위 테스트: 게시글 단일 조회 API")
    void getPost() throws Exception {
        // Given
        Long id = 1L;
        PostDetailResponseDto mockResponse = PostDetailResponseDto.builder()
                .id(id)
                .title("Title for testing single post read.")
                .content("Content for testing single post read.")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        // When
        when(postReadService.findById(id)).thenReturn(mockResponse);

        // Then
        mockMvc.perform(get("/api/v1/post/detail/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("post-get",
                        pathParameters(
                                parameterWithName("id").description("조회할 게시글 ID")
                        ),
                        responseFields(
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("응답 상태 메시지"),
                                fieldWithPath("status").type(JsonFieldType.NUMBER)
                                        .description("HTTP 상태 코드"),
                                subsectionWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터 객체"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                                        .description("게시글 ID"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                        .description("게시글 제목"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING)
                                        .description("게시글 내용"),
                                fieldWithPath("data.createdDate").type(JsonFieldType.STRING)
                                        .description("게시글 작성일자 (ISO-8601 형식)"),
                                fieldWithPath("data.updatedDate").type(JsonFieldType.STRING)
                                        .description("게시글 수정일자 (ISO-8601 형식)")
                        )
                ));
    }

    @Test
    @DisplayName("단위 테스트: 게시글 목록 조회 API")
    void getPosts() throws Exception {
        // Given
        List<PostSimpleResponseDto> mockResponse = List.of(
                PostSimpleResponseDto.builder()
                        .id(1L)
                        .title("First Title for testing multiple posts read.")
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .build(),
                PostSimpleResponseDto.builder()
                        .id(2L)
                        .title("Second Title for testing multiple posts read.")
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .build()
        );

        // When
        when(postReadService.findAll()).thenReturn(mockResponse);

        // Then
        mockMvc.perform(get("/api/v1/post/list")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("post-list",
                        responseFields(
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("응답 상태 메시지"),
                                fieldWithPath("status").type(JsonFieldType.NUMBER)
                                        .description("HTTP 상태 코드"),
                                fieldWithPath("data[]").type(JsonFieldType.ARRAY)
                                        .description("게시글 목록").optional(),
                                fieldWithPath("data[].id").type(JsonFieldType.NUMBER)
                                        .description("게시글 ID"),
                                fieldWithPath("data[].title").type(JsonFieldType.STRING)
                                        .description("게시글 제목"),
                                fieldWithPath("data[].createdDate").type(JsonFieldType.STRING)
                                        .description("게시글 작성일자 (ISO-8601 형식)"),
                                fieldWithPath("data[].updatedDate").type(JsonFieldType.STRING)
                                        .description("게시글 수정일자 (ISO-8601 형식)")
                        )
                ));
    }

    @Test
    @DisplayName("단위 테스트: 게시글 검색 API")
    void searchPosts() throws Exception {
        // Given
        Map<String, Object> params = Map.of(
                "title", "title",
                "content", "content",
                "sCreatedDate", LocalDateTime.now(),
                "eCreatedDate", LocalDateTime.now(),
                "sUpdatedDate", LocalDateTime.now(),
                "eUpdatedDate", LocalDateTime.now()
        );
        String requestBody = objectMapper.writeValueAsString(params);
        List<PostSimpleResponseDto> mockResponse = List.of(
                PostSimpleResponseDto.builder()
                        .id(1L)
                        .title("First Title for testing multiple posts search.")
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .build(),
                PostSimpleResponseDto.builder()
                        .id(2L)
                        .title("Second Title for testing multiple posts search.")
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .build()
        );

        // When
        when(postReadService.searchBy(any())).thenReturn(mockResponse);

        // Then
        mockMvc.perform(post("/api/v1/post/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andDo(document("post-search",
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("검색할 게시글 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("검색할 게시글 내용"),
                                fieldWithPath("sCreatedDate").type(JsonFieldType.STRING)
                                        .description("검색할 게시글 작성일자 시작점 (ISO-8601 형식)"),
                                fieldWithPath("eCreatedDate").type(JsonFieldType.STRING)
                                        .description("검색할 게시글 작성일자 종료점 (ISO-8601 형식)"),
                                fieldWithPath("sUpdatedDate").type(JsonFieldType.STRING)
                                        .description("검색할 게시글 수정일자 시작점 (ISO-8601 형식)"),
                                fieldWithPath("eUpdatedDate").type(JsonFieldType.STRING)
                                        .description("검색할 게시글 수정일자 종료점 (ISO-8601 형식)")
                        ),
                        responseFields(
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("응답 상태 메시지"),
                                fieldWithPath("status").type(JsonFieldType.NUMBER)
                                        .description("HTTP 상태 코드"),
                                fieldWithPath("data[]").type(JsonFieldType.ARRAY)
                                        .description("게시글 목록").optional(),
                                fieldWithPath("data[].id").type(JsonFieldType.NUMBER)
                                        .description("게시글 ID"),
                                fieldWithPath("data[].title").type(JsonFieldType.STRING)
                                        .description("게시글 제목"),
                                fieldWithPath("data[].createdDate").type(JsonFieldType.STRING)
                                        .description("게시글 작성일자 (ISO-8601 형식)"),
                                fieldWithPath("data[].updatedDate").type(JsonFieldType.STRING)
                                        .description("게시글 수정일자 (ISO-8601 형식)")
                        )
                ));
    }

    @Test
    @DisplayName("단위 테스트: 게시글 생성 API")
    void createPost() throws Exception {
        // Given
        PostCreateRequestDto mockRequest = PostCreateRequestDto.builder()
                .title("Title for testing post create.")
                .content("Content for testing post create.")
                .build();
        String requestBody = objectMapper.writeValueAsString(mockRequest);
        PostDetailResponseDto mockResponse = PostDetailResponseDto.builder()
                .id(1L)
                .title("Title for testing post create.")
                .content("Content for testing post create.")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        // When
        when(postWriteService.create(mockRequest)).thenReturn(mockResponse);

        // Then
        mockMvc.perform(post("/api/v1/post/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andDo(document("post-create",
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("생성할 게시글 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("생성할 게시글 내용")
                        ),
                        responseFields(
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("응답 상태 메시지"),
                                fieldWithPath("status").type(JsonFieldType.NUMBER)
                                        .description("HTTP 상태 코드"),
                                subsectionWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터 객체"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                                        .description("생성된 게시글 ID"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                        .description("생성된 게시글 제목"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING)
                                        .description("생성된 게시글 내용"),
                                fieldWithPath("data.createdDate").type(JsonFieldType.STRING)
                                        .description("생성된 게시글 작성일자 (ISO-8601 형식)"),
                                fieldWithPath("data.updatedDate").type(JsonFieldType.STRING)
                                        .description("생성된 게시글 수정일자 (ISO-8601 형식)")
                        )
                ));
    }

    @Test
    @DisplayName("단위 테스트: 게시글 수정 API")
    void updatePost() throws Exception {
        // Given
        PostUpdateRequestDto mockRequest = PostUpdateRequestDto.builder()
                .id(1L)
                .title("Title for testing post update.")
                .content("Content for testing post update.")
                .build();
        String requestBody = objectMapper.writeValueAsString(mockRequest);
        PostDetailResponseDto mockResponse = PostDetailResponseDto.builder()
                .id(1L)
                .title("Title for testing post update")
                .content("Content for testing post update")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        // When
        when(postWriteService.update(mockRequest)).thenReturn(mockResponse);

        // Then
        mockMvc.perform(put("/api/v1/post/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andDo(document("post-update",
                        requestFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("수정할 게시글 ID"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("수정할 게시글 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("수정할 게시글 내용")
                        ),
                        responseFields(
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("응답 상태 메시지"),
                                fieldWithPath("status").type(JsonFieldType.NUMBER)
                                        .description("HTTP 상태 코드"),
                                subsectionWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터 객체"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                                        .description("수정된 게시글 ID"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                        .description("수정된 게시글 제목"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING)
                                        .description("수정된 게시글 내용"),
                                fieldWithPath("data.createdDate").type(JsonFieldType.STRING)
                                        .description("수정된 게시글 작성일자 (ISO-8601 형식)"),
                                fieldWithPath("data.updatedDate").type(JsonFieldType.STRING)
                                        .description("수정된 게시글 수정일자 (ISO-8601 형식)")
                        )
                ));
    }

    @Test
    @DisplayName("단위 테스트: 게시글 삭제 API")
    void deletePost() throws Exception {
        // Given
        Long id = 1L;
        PostDetailResponseDto mockResponse = PostDetailResponseDto.builder()
                .id(id)
                .title("Title for testing post delete.")
                .content("Content for testing post delete.")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        // When
        when(postWriteService.delete(id)).thenReturn(mockResponse);

        // Then
        mockMvc.perform(delete("/api/v1/post/delete/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("post-delete",
                        pathParameters(
                                parameterWithName("id").description("삭제할 게시글 ID")
                        ),
                        responseFields(
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("응답 상태 메시지"),
                                fieldWithPath("status").type(JsonFieldType.NUMBER)
                                        .description("HTTP 상태 코드"),
                                subsectionWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터 객체"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                                        .description("삭제된 게시글 ID"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                        .description("삭제된 게시글 제목"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING)
                                        .description("삭제된 게시글 내용"),
                                fieldWithPath("data.createdDate").type(JsonFieldType.STRING)
                                        .description("삭제된 게시글 작성일자 (ISO-8601 형식)"),
                                fieldWithPath("data.updatedDate").type(JsonFieldType.STRING)
                                        .description("삭제된 게시글 수정일자 (ISO-8601 형식)")
                        )
                ));
    }
}