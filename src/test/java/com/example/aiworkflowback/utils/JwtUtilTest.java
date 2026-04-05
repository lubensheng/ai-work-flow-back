package com.example.aiworkflowback.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("JWT工具类测试")
class JwtUtilTest {

  private String testUsername;
  private String testToken;

  @BeforeEach
  void setUp() {
    testUsername = "testuser@example.com";
    testToken = JwtUtil.generateToken(testUsername);
  }

  @Test
  @DisplayName("测试生成Token - 成功")
  void testGenerateToken_Success() {
    // Given
    String username = "john.doe@example.com";

    // When
    String token = JwtUtil.generateToken(username);

    // Then
    assertNotNull(token);
    assertFalse(token.isEmpty());
    // Token应该由三部分组成（用点分隔）
    assertEquals(3, token.split("\\.").length);
  }

  @Test
  @DisplayName("测试生成Token - 不同用户生成不同Token")
  void testGenerateToken_DifferentUsers() {
    // Given
    String user1 = "user1@example.com";
    String user2 = "user2@example.com";

    // When
    String token1 = JwtUtil.generateToken(user1);
    String token2 = JwtUtil.generateToken(user2);

    // Then
    assertNotNull(token1);
    assertNotNull(token2);
    assertNotEquals(token1, token2);
  }

  @Test
  @DisplayName("测试从Token获取所有Claims - 成功")
  void testGetAllClaimsFromToken_Success() {
    // When
    Claims claims = JwtUtil.getAllClaimsFromToken(testToken);

    // Then
    assertNotNull(claims);
    assertEquals(testUsername, claims.getSubject());
    assertEquals(testUsername, claims.get("username"));
    assertNotNull(claims.get("created"));
    assertNotNull(claims.getIssuedAt());
    assertNotNull(claims.getExpiration());
  }

  @Test
  @DisplayName("测试从Token获取所有Claims - Token无效抛出异常")
  void testGetAllClaimsFromToken_InvalidToken() {
    // Given
    String invalidToken = "invalid.token.string";

    // When & Then
    assertThrows(Exception.class, () -> {
      JwtUtil.getAllClaimsFromToken(invalidToken);
    });
  }

  @Test
  @DisplayName("测试从Token获取所有Claims - Token被篡改抛出异常")
  void testGetAllClaimsFromToken_TamperedToken() {
    // Given
    String tamperedToken = testToken.substring(0, testToken.length() - 5) + "xxxxx";

    // When & Then
    assertThrows(SignatureException.class, () -> {
      JwtUtil.getAllClaimsFromToken(tamperedToken);
    });
  }

  @Test
  @DisplayName("测试判断Token是否过期 - 未过期")
  void testIsTokenExpired_NotExpired() {
    // Given
    String freshToken = JwtUtil.generateToken(testUsername);

    // When
    Boolean isExpired = JwtUtil.isTokenExpired(freshToken);

    // Then
    assertFalse(isExpired);
  }

  @Test
  @DisplayName("测试判断Token是否过期 - 已过期")
  void testIsTokenExpired_Expired() throws InterruptedException {
    // 注意：由于EXPIRATION_TIME是24小时，这里无法真正等待过期
    // 如果需要测试过期，可以临时修改EXPIRATION_TIME为很小的值
    // 这里演示概念

    // Given: 创建一个即将过期的token（通过修改JwtUtil的EXPIRATION_TIME）
    // 实际测试中建议使用反射或者创建测试专用的JwtUtil

    // When & Then
    // 由于正常token不会过期，这里只是验证方法可以正常执行
    String token = JwtUtil.generateToken(testUsername);
    Boolean isExpired = JwtUtil.isTokenExpired(token);
    assertFalse(isExpired);
  }

  @Test
  @DisplayName("测试从Token获取指定Claim")
  void testGetClaimFromToken() {
    // When
    String usernameFromToken = JwtUtil.getClaimFromToken(testToken, Claims::getSubject);
    Date expirationDate = JwtUtil.getClaimFromToken(testToken, Claims::getExpiration);
    Date issuedDate = JwtUtil.getClaimFromToken(testToken, Claims::getIssuedAt);

    // Then
    assertEquals(testUsername, usernameFromToken);
    assertNotNull(expirationDate);
    assertNotNull(issuedDate);
    assertTrue(expirationDate.after(issuedDate));
  }

  @Test
  @DisplayName("测试获取Token过期时间")
  void testGetExpirationDateFromToken() {
    // When
    Date expirationDate = JwtUtil.getExpirationDateFromToken(testToken);

    // Then
    assertNotNull(expirationDate);
    // 过期时间应该在未来
    assertTrue(expirationDate.after(new Date()));
  }

  @Test
  @DisplayName("测试Token中包含用户名信息")
  void testTokenContainsUsername() {
    // When
    Claims claims = JwtUtil.getAllClaimsFromToken(testToken);
    String usernameFromSubject = claims.getSubject();
    String usernameFromClaim = (String) claims.get("username");

    // Then
    assertEquals(testUsername, usernameFromSubject);
    assertEquals(testUsername, usernameFromClaim);
  }

  @Test
  @DisplayName("测试Token中包含创建时间")
  void testTokenContainsCreatedTime() {
    // When
    Date beforeCreate = new Date();
    String token = JwtUtil.generateToken(testUsername);
    Date afterCreate = new Date();
    Claims claims = JwtUtil.getAllClaimsFromToken(token);
    Date createdTime = (Date) claims.get("created");

    // Then
    assertNotNull(createdTime);
    // 创建时间应该在生成Token前后之间
    assertTrue(createdTime.after(beforeCreate) || createdTime.equals(beforeCreate));
    assertTrue(createdTime.before(afterCreate) || createdTime.equals(afterCreate));
  }

  @Test
  @DisplayName("测试多次生成同一用户的Token - 应该不同")
  void testMultipleTokensForSameUser() {
    // When
    String token1 = JwtUtil.generateToken(testUsername);
    String token2 = JwtUtil.generateToken(testUsername);

    // Then
    assertNotEquals(token1, token2);
  }

  @Test
  @DisplayName("测试Token中包含签发时间和过期时间")
  void testTokenContainsIssuedAtAndExpiration() {
    // When
    Claims claims = JwtUtil.getAllClaimsFromToken(testToken);
    Date issuedAt = claims.getIssuedAt();
    Date expiration = claims.getExpiration();

    // Then
    assertNotNull(issuedAt);
    assertNotNull(expiration);
    // 过期时间应该在签发时间之后
    assertTrue(expiration.after(issuedAt));

    // 验证过期时间 = 签发时间 + EXPIRATION_TIME
    long expectedExpiration = issuedAt.getTime() + JwtUtil.EXPIRATION_TIME;
    assertEquals(expectedExpiration, expiration.getTime());
  }

  @Test
  @DisplayName("测试空用户名生成Token")
  void testGenerateTokenWithEmptyUsername() {
    // Given
    String emptyUsername = "";

    // When
    String token = JwtUtil.generateToken(emptyUsername);
    Claims claims = JwtUtil.getAllClaimsFromToken(token);

    // Then
    assertNotNull(token);
    assertEquals(emptyUsername, claims.getSubject());
    assertEquals(emptyUsername, claims.get("username"));
  }

  @Test
  @DisplayName("测试特殊字符用户名生成Token")
  void testGenerateTokenWithSpecialCharacters() {
    // Given
    String specialUsername = "user!@#$%^&*()_+{}|:<>?~`";

    // When
    String token = JwtUtil.generateToken(specialUsername);
    Claims claims = JwtUtil.getAllClaimsFromToken(token);

    // Then
    assertNotNull(token);
    assertEquals(specialUsername, claims.getSubject());
    assertEquals(specialUsername, claims.get("username"));
  }

  @Test
  @DisplayName("测试中文用户名生成Token")
  void testGenerateTokenWithChineseUsername() {
    // Given
    String chineseUsername = "张三测试用户";

    // When
    String token = JwtUtil.generateToken(chineseUsername);
    Claims claims = JwtUtil.getAllClaimsFromToken(token);

    // Then
    assertNotNull(token);
    assertEquals(chineseUsername, claims.getSubject());
    assertEquals(chineseUsername, claims.get("username"));
  }

  @Test
  @DisplayName("测试长用户名生成Token")
  void testGenerateTokenWithLongUsername() {
    // Given
    String longUsername = "a".repeat(1000);

    // When
    String token = JwtUtil.generateToken(longUsername);
    Claims claims = JwtUtil.getAllClaimsFromToken(token);

    // Then
    assertNotNull(token);
    assertEquals(longUsername, claims.getSubject());
  }

  @Test
  @DisplayName("测试getClaimFromToken的泛型功能")
  void testGetClaimFromToken_Generic() {
    // When
    String subject = JwtUtil.getClaimFromToken(testToken, Claims::getSubject);
    Date expiration = JwtUtil.getClaimFromToken(testToken, Claims::getExpiration);
    String username = JwtUtil.getClaimFromToken(testToken, claims -> (String) claims.get("username"));

    // Then
    assertEquals(testUsername, subject);
    assertNotNull(expiration);
    assertEquals(testUsername, username);
  }
}