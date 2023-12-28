package com.example.controller

import com.example.model.Response
import com.example.model.ResponseContents.SwaggerExamples.NO_USER_NAME
import com.example.model.ResponseContents.SwaggerExamples.NO_USER_VALUE
import com.example.model.ResponseContents.SwaggerExamples.OK_RESPONSE_NAME
import com.example.model.ResponseContents.SwaggerExamples.OK_RESPONSE_NO_DATA_VALUE
import com.example.model.ResponseContents.SwaggerExamples.OK_RESPONSE_USER
import com.example.model.ResponseContents.SwaggerExamples.OK_RESPONSE_USERS
import com.example.model.ResponseContents.SwaggerExamples.USER_EXISTS_NAME
import com.example.model.ResponseContents.SwaggerExamples.USER_EXISTS_VALUE
import com.example.model.jpa.entity.User
import com.example.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Test APIs")
class UserController(val userService: UserService) {
    @GetMapping
    @Operation(summary = "유저 리스트", description = "등록된 유저 리스트를 가져오는 API")
    @ApiResponse(responseCode = "200",
            content = [
                Content(examples = [
                    ExampleObject(name = OK_RESPONSE_NAME, value = OK_RESPONSE_USERS, description = "등록된 유저 리스트")
                ], schema = Schema(implementation = User::class))]
            , description = "유저 리스트")
    fun get() = userService.get()

    @PostMapping(consumes = [APPLICATION_FORM_URLENCODED_VALUE])
    @Operation(summary = "유저 등록", description = "유저를 등록시키는 API")
    @ApiResponse(responseCode = "200",
            content = [
                Content(examples = [
                    ExampleObject(name = OK_RESPONSE_NAME, value = OK_RESPONSE_USER, description = "정상적으로 유저 등록"),
                    ExampleObject(name = USER_EXISTS_NAME, value = USER_EXISTS_VALUE, description = "이미 유저가 존재")
                ], schema = Schema(implementation = Response::class))]
            , description = "유저 등록")
    fun postFormUrlencoded(
            @Schema(implementation = User::class) user: User
    ) = userService.post(user)

    @PostMapping(consumes = [APPLICATION_JSON_VALUE])
    fun postJson(
            @Schema(implementation = User::class)
            @RequestBody user: User
    ) = userService.post(user)

    @PutMapping(consumes = [APPLICATION_FORM_URLENCODED_VALUE])
    @Operation(summary = "유저 정보 수정", description = "유저 정보를 수정하는 API")
    @ApiResponse(responseCode = "200",
            content = [
                Content(examples = [
                    ExampleObject(name = OK_RESPONSE_NAME, value = OK_RESPONSE_USER, description = "정상적으로 유저 정보 수정"),
                    ExampleObject(name = NO_USER_NAME, value = NO_USER_VALUE, description = "유저가 없음")
                ], schema = Schema(implementation = Response::class))]
            , description = "유저 정보 수정")
    fun putFormUrlencoded(@Schema(implementation = User::class) user: User) = userService.put(user)

    @PutMapping(consumes = [APPLICATION_JSON_VALUE])
    fun putJson(@Schema(implementation = User::class) user: User) = userService.put(user)

    @DeleteMapping
    @Operation(summary = "유저 삭제", description = "유저를 삭제하는 API")
    @ApiResponse(responseCode = "200",
            content = [
                Content(examples = [
                    ExampleObject(name = OK_RESPONSE_NAME, value = OK_RESPONSE_NO_DATA_VALUE, description = "유저 삭제"),
                    ExampleObject(name = NO_USER_NAME, value = NO_USER_VALUE, description = "유저가 없음")
                ], schema = Schema(implementation = Response::class))]
            , description = "유저 삭제")
    fun deleteFormUrlencoded(id: String) = userService.delete(id)
}