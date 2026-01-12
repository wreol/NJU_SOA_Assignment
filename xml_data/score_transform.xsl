<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:jw="http://jw.nju.edu.cn/schema"
                exclude-result-prefixes="jw">

    <xsl:output method="xml" indent="yes"/>

    <xsl:key name="coursesKey" match="jw:Course" use="@courseId"/>

    <xsl:template match="/">
        <CourseGradeList>
            <xsl:for-each select="//jw:Course[generate-id() = generate-id(key('coursesKey', @courseId)[1])]">
                <xsl:variable name="currentCourseId" select="@courseId"/>

                <Course id="{$currentCourseId}">
                    <xsl:for-each select="key('coursesKey', $currentCourseId)">
                        <xsl:sort select="jw:scoreTotal" data-type="number" order="descending"/>

                        <StudentScore>
                            <xsl:attribute name="studentId">
                                <xsl:value-of select="../jw:id"/>
                            </xsl:attribute>
                            <xsl:attribute name="score">
                                <xsl:value-of select="jw:scoreTotal"/>
                            </xsl:attribute>

                            <xsl:copy-of select="*"/>
                        </StudentScore>
                    </xsl:for-each>
                </Course>
            </xsl:for-each>
        </CourseGradeList>
    </xsl:template>
</xsl:stylesheet>