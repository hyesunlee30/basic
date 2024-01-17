package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemberRepository implements MemberRepository{
    @Autowired
    private DataSource dataSource;


    @Override
    public Member save(Member member) {
        try {
            Connection connection = dataSource.getConnection();
            String sql = "insert into member(name, email,password) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,member.getName());
            preparedStatement.setString(2,member.getEmail());
            preparedStatement.setString(3,member.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public List<Member> findAll() {
        List<Member> list = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            String sql = "select * from member";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Member member = Member.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .create_time(rs.getTimestamp("create_time").toLocalDateTime())
                        .build();
                list.add(member);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<Member> findById(int id) {
        Optional<Member> member = Optional.empty();
        try {
            Connection connection = dataSource.getConnection();
            String sql = "select * from member where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            member = Optional.ofNullable(Member.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .password(rs.getString("password"))
                    .create_time(rs.getTimestamp("create_time").toLocalDateTime())
                    .build());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }
}
