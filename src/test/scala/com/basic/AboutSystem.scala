package com.basic

object AboutSystem {

  def main(args: Array[String]): Unit = {
    val env: String = s"${System.getProperty("ENV")}";

    println(env)
  }
}
