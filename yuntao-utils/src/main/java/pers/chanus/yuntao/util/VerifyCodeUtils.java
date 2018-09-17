/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 生成验证码图片
 * 
 * @author Chanus
 * @date 2018-08-30 17:36:49
 * @since 0.0.1
 */
public class VerifyCodeUtils {
	/** 验证码图片的宽度 */
	private int width;
	/** 验证码图片的高度 */
	private int height;
	/** x坐标，即字符左端距图片左边界距离 */
	private int x;
	/** y坐标，即字符顶端距图片下边界距离 */
	private int y;
	/** 验证码字符个数 */
	private int codeCount;
	/** 验证码字符宽度微调值 */
	private int codeWidthX;
	/** 验证码字符高度微调值 */
	private int codeHeightY;
	/** 验证码字符宽度 */
	private int codeWidth;
	/** 验证码字符高度 */
	private int codeHeight;
	/** 验证码字符集 */
	private char[] codeSequence;

	/**
	 * 初始化默认参数
	 * @since 0.0.1
	 */
	public VerifyCodeUtils() {
		super();
		this.width = 120;
		this.height = 38;
		this.x = 16;
		this.y = 32;
		this.codeCount = 4;
		this.codeWidthX = 1;
		this.codeHeightY = 6;
		this.codeWidth = (this.width - 2 * x) / this.codeCount + this.codeWidthX;
		this.codeHeight = 2 * y - this.height + this.codeHeightY;
		this.codeSequence = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	}

	/**
	 * 初始化参数
	 * 
	 * @param width	验证码图片的宽度
	 * @param height	验证码图片的高度
	 * @param x	x坐标，即字符左端距图片左边界距离
	 * @param y	y坐标，即字符顶端距图片下边界距离
	 * @param codeWidthX	验证码字符宽度微调值
	 * @param codeHeightY	验证码字符高度微调值
	 * @param codeCount	验证码字符个数
	 * @param codeSequence	验证码字符集
	 * @since 0.0.1
	 */
	public void init(int width, int height, int x, int y, int codeWidthX, int codeHeightY, int codeCount, char[] codeSequence) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.codeCount = codeCount;
		this.codeWidth = (width - 2 * x) / codeCount + codeWidthX;
		this.codeHeight = 2 * y - height + codeHeightY;
		this.codeSequence = codeSequence;
	}

	/**
	 * 生成验证码图片
	 * 
	 * @return Map	randomCode-验证码，buffImg-验证码图片
	 * @throws IOException
	 * @since 0.0.1
	 */
	public Map<String, Object> generate() throws IOException {
		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		// 创建一个随机数生成器类
		Random random = new Random();
		// 将图像填充为白色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, codeHeight);
		// 设置字体。
		g.setFont(font);
		// 画边框。
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, width - 1, height - 1);
		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		// 随机产生codeCount数字的验证码。
		String randomString = null;
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			randomString = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
			// 用随机产生的颜色将验证码绘制到图像中。
			g.setColor(new Color(255, 0, 0));
			g.drawString(randomString, i * codeWidth + x, y);
			// 将产生的四个随机数组合在一起。
			randomCode.append(randomString);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("randomCode", randomCode);
		map.put("buffImg", buffImg);
		return map;
	}
}