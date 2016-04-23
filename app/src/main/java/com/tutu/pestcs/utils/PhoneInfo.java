package com.tutu.pestcs.utils;

import android.content.Context;

public class PhoneInfo {

	public static String brand;// 品牌

	public static char cell_id; // 小区识别码,蜂窝ID,
	// 三种主要的基于位置服务(LBS)技术之一。小区识别码通过识别网络中哪一个小区传输用户呼叫并将该信息翻译成纬度和经度来确定用户位置。

	public static byte density; // 屏幕的密度

	public static int height; // 屏幕高

	public static String imei;// imei号

	public static String imsi; // imsi号

	/*
	 * location area code 位置区码
	 * （移动通信系统中）,是为寻呼而设置的一个区域,覆盖一片地理区域,初期一般按行政区域划分(一个县或一个区
	 * ),现在很灵活了,按寻呼量划分.当一个LAC下的寻呼量达到一个预警门限,就必须拆分.
	 * 为了确定移动台的位置，每个GSMPLMN的覆盖区都被划分成许多位置区，位置区码(LAC)则用于标识不同的位置区。
	 * 位置区码(LAC)包含于LAI中，
	 * 由两个字节组成，采用16进制编码。可用范围为0x0000－0xFFFF，码组0x0000和0xFFFE不可以使用(
	 * 参见GSM规范03.03、04.08和11.11)。一个位置区可以包含一个或多个小区。
	 */
	public static char lac;

	/*
	 * Mobile Network Code 的缩写，译为移动网络代码。它由二到三位数字组成。它和 MCC 合在一起唯一标识一个移动网络提供者。比如
	 * 中国移动的 MNC 是 00，中国联通的 MNC 是 01，中国联通 CDMA 的 MNC 是 03，中国卫星全球星网的 MNC 是 04。*
	 */
	public static String mnc;

	/*
	 * Mobile Country Code 的缩写，译为移动国家代码。它由三位数字组成。用于标识一个国家，但一个国家可以被分配多个 MCC 中国的
	 * MCC 只有 460*
	 */
	public static String mcc;

	/*
	 * 业务类型 0移动，1联通，2电信 取值根据mnc
	 */
	public static char businessType;

	/**
	 * phone type
	 */
	public static String model;// 手机型号

	public static String phoneId; // Android 设备的唯一标识码

	public static int ramSize; // 内存大小。手机上的RAM是指系统运行及软件运行可需要的临时空间，跟电脑上的内存是相同的意思。

	public static int romSize; // rom大小。手机上的ROM是指手机系统及可安装程序的空间，ROM越大，能直接在系统里安装的程序就越多，相当于我们电脑的C盘。

	public static int sdk_version_code; // sdk版本号

	public static int width;// 屏幕宽度

	public static char netType;// 网络类型,wifi为0，gprs为1,3g为2

	public static String mac; // mac地址

	public static boolean isLowPhone() {
		return ramSize > 0 && ramSize <= 256;
	}

	/**
	 * 新增网络类型
	 */
	public static int netType2;// 网络标识，手机的网络制式。网络类型非wifi时，该值有效

	private PhoneInfo() {
	}

	/**
	 * 初始化终端信息
	 */
	public static void initPhoneInfo(Context context) {

		PhoneInfo.brand = DeviceUtils.getBrand();
		PhoneInfo.model = DeviceUtils.getModel();
		PhoneInfo.sdk_version_code = DeviceUtils.getSdkVersionCode();
		PhoneInfo.imsi = DeviceUtils.getIMSI(context);
		PhoneInfo.imei = DeviceUtils.getIMEI(context);
		PhoneInfo.lac = DeviceUtils.getLac();

		PhoneInfo.cell_id = DeviceUtils.getCellID();
		PhoneInfo.mac = DeviceUtils.getMacAddress(context);
		int mcc = DeviceUtils.getMcc(context);
		int mnc = DeviceUtils.getMnc(context);
		PhoneInfo.mcc = mcc + "";
		/*
		 * 1 联通 2 移动 3 电信
		 */
		PhoneInfo.mnc = mnc + "";
		switch (mnc) {
			case 1:
				PhoneInfo.businessType = 1;
				break;
			case 2:
				PhoneInfo.businessType = 0;
				break;
			case 3:
				PhoneInfo.businessType = 2;
				break;
			default:
				PhoneInfo.businessType = 0;
				break;
		}
		PhoneInfo.width = DeviceUtils.getWidthPixels(context);
		PhoneInfo.height = DeviceUtils.getHeightPixels(context);
		PhoneInfo.phoneId = DeviceUtils.getPhoneId(context);
		float density = DeviceUtils.getDensity(context);
		PhoneInfo.ramSize = DeviceUtils.getRamSize(context);
		PhoneInfo.romSize = DeviceUtils.getMemSize(context);
		if (density == 0.75f) {
			PhoneInfo.density = 0;
		} else if (density == 1f) {
			PhoneInfo.density = 1;
		} else if (density == 1.5f) {
			PhoneInfo.density = 2;
		} else {
			PhoneInfo.density = 3;
		}

	}

	public static String println() {
		final StringBuffer sb = new StringBuffer("");
		sb.append("brand=").append(brand);
		sb.append("\nmodel=").append(model);
		sb.append("\nphoneId=").append(phoneId);
		sb.append("\ndensity=").append(density);
		sb.append("\nheight=").append(height);
		sb.append("\nwidth=").append(width);
		sb.append("\nimsi=").append(imsi);
		sb.append("\nimei=").append(imei);
		sb.append("\nramSize=").append(ramSize);
		sb.append("\nromSize=").append(romSize);
		sb.append("\nisLowPhone=").append(isLowPhone());
		sb.append("\nsdkVersionCode=").append(sdk_version_code);

		return sb.toString();
	}
}
