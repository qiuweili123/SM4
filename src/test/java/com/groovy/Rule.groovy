package com.groovy

import java.text.ParseException
import java.text.SimpleDateFormat

class Rule2 {
    //以下是英文部分变量,一般通过程序自动装载得到,用于从数据库或其他持久层加载业务数据



    def collegeName = "EMBA业余大学"
    def tuitionFee = 80000
    def startDate = '2016/02/01', finishDate = '2016/09/01'
    def today = "2017/01/21";

    def student = ['id'            : 'E9527',
                   'name'          : '于小小',
                   'gender'        : 'F',
                   'kind'          : 'EMBA',
                   'className'     : '重庆理工大学MBA三年级四班',
                   'grade'         : 4,
                   'birth'         : '1989/03/02',
                   'address'       : '重庆市巴南区红光大道',
                   'salary'        : 50000,
                   'createTime'    : '2016/01/21 11:31:00',
                   'courses'       : [
                           ['id': 'GJC', name: '公共基础', 'classHour': 32],
                           ['id': 'ZXW', name: '组织行为学', 'classHour': 40],
                           ['id': 'TJX', name: '统计学', 'classHour': 20],
                           ['id': 'CJR', name: '财务与金融', 'classHour': 60],
                           ['id': 'JJF', name: '经济法', 'classHour': 48],
                           ['id': 'JSJ', name: '计算机技能', 'classHour': 16],
                   ],
                   'mainInstructor': ['id': 'TE007', 'name': '杨大大'],
                   'extra'         : [
                           'attendanceLog': ['2016/02/01 09:00:02', '2016/02/03 08:50:03', '2016/02/15 09:12:34', '2016/04/01 07:30:11'],
                           'progressState': 'finished'
                   ]


    ];
    def toDate(_date){
        try{
            return (new SimpleDateFormat("yyyy/MM/dd hh:mm:ss")).parse(_date);
        }catch(ParseException e){
            return (new SimpleDateFormat("yyyy/MM/dd")).parse(_date);
        }

    }


    def 规则集 = [
            '基础信息':
                    [
                            '是否90后': student.birth >= '1990/01/01'?'是':'否',
                            '性别'   : student.gender == 'M' ? '男' : '女',
                            '注册天数':toDate(today) - toDate(student.createTime),
                            '地区':{
                                def province = student.address.subSequence(0,3);
                                //做一个区域和省级行政单位的映射关系
                                def areaMapping = ['西南':['重庆市','四川省','贵州省','云南省'],'江浙沪':['上海市','江苏省','浙江省'],'京津冀':['北京市','天津市','河北省']];
                                //进行筛选
                                def entry = areaMapping.find {key,value ->
                                    value.contains(province);
                                }
                                entry.key
                            }()//最后这个"()"一定要,否则闭包不执行
                    ],

            '评级'  : [
                    //使用三元表达式,大于6W --> A ,4-6W --> B,2-4W -->C,2W以下 --> D
                    '学费档次' : tuitionFee>=60000?'A':(tuitionFee>=40000&&tuitionFee<60000?'B':(tuitionFee>=20000&&tuitionFee<40000)?'C':'D'),
                    '收入档次':{
                        if(student.salary>=20000) '高收入'
                        else if(student.salary>=10000) '中等收入'
                        else if(student.salary>=5000) '一般收入'
                        else '低收入'
                    }() //最后这个"()"一定要,否则闭包不执行
            ],
            '学习情况': [
                    '总学时':{
                        int totalHourse = 0;
                        student.courses.each { totalHourse += it.classHour}
                        totalHourse
                    }(),
                    '迟到次数':{
                        int _count = 0
                        student.extra.attendanceLog.each {
                            Date _date = toDate(it)
                            _count += (_date.hours>=9&&_date.seconds>=1)?1:0
                        }
                        _count
                    }(),
                    '出勤次数':student.extra.attendanceLog.size(),
                    '单门课程最长学时':{
                        int maxHour = 0;
                        student.courses.each { maxHour = Math.max(maxHour,it.classHour)}
                        student.courses.find({it.classHour == maxHour}) //默认最后一句为返回值
                    }(),
            ]
    ]

}

public static void main(String[] args) {

// ========运行验证========

//创建运行时对象
    def rctx = new Rule2()
//输出结果看
//----简单的规则取值


    println rctx.collegeName
    println "hh=${rctx.规则集.基础信息.是否90后}"
    println "基础信息.是否90后  =  ${rctx.规则集.基础信息.是否90后}"
    println "基础信息.性别      = ${rctx.规则集.基础信息['性别']}"
    println "基础信息.注册天数      = ${rctx.规则集.基础信息.注册天数}"
    println "评级.学费档次      = ${rctx.规则集.评级.学费档次}"
//----使用闭包的方式取值
    println "基础信息.地区      = ${rctx.规则集.基础信息.地区}"
    println "评级.收入档次      = ${rctx.规则集.评级.收入档次}"
    println "学习情况.总学时      = ${rctx.规则集.学习情况.总学时}"
    println "学习情况.迟到次数      = ${rctx.规则集.学习情况.迟到次数}"
    println "学习情况.出勤次数      = ${rctx.规则集.学习情况.出勤次数}"
    println "学习情况.单门课程最长学时      = ${rctx.规则集.学习情况.单门课程最长学时}"
}