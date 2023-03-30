package net.egyptiandictionary

import groovy.sql.Sql

def URL = 'jdbc:mysql://localhost/egyptiandictionary?useUnicode=yes&characterEncoding=UTF-8'
def user = "root"
def password = "Tk02030#"
def driver = "com.mysql.cj.jdbc.Driver"//"com.mysql.jdbc.Driver"

def SQL = Sql.newInstance(URL, user, password, driver)

SQL.eachRow("select * from dictionary") {
    println it
}