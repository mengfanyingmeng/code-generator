package common;


import cn.hutool.core.util.StrUtil;


/**
 * 业务异常
 */
public class BizException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 是否禁止打印error日志
	 * 默认为false，打印error日志
	 * true则打印warn日志, false打印error日志
	 **/
	private boolean noPrintErr;

	//错误码
	private String code;

	public BizException(String code, String message){
		super(message);
		this.code = code;
	}

	public BizException(boolean noPrintErr, String code, String message){
		super(message);
		this.code = code;
		this.noPrintErr = noPrintErr;
	}

	public BizException(BaseEnumResult baseResult) {
		super(baseResult.getMessage());
		this.code = baseResult.getCode();
	}

	public BizException(BaseEnumResult baseResult, Object... params) {
		super(StrUtil.format(baseResult.getMessage(), params));
		this.code = baseResult.getCode();
	}

	/**
	 *
	 * @param noPrintErr true: log.warn, false: log.error
	 * @param baseResult
	 */
	public BizException(boolean noPrintErr, BaseEnumResult baseResult) {
		super(baseResult.getMessage());
		this.noPrintErr = noPrintErr;
		this.code = baseResult.getCode();
	}

	public BizException(boolean noPrintErr, BaseEnumResult baseResult, Object... params) {
		super(StrUtil.format(baseResult.getMessage(), params));
		this.noPrintErr = noPrintErr;
		this.code = baseResult.getCode();
	}

	public String getCode() {
		return code;
	}

	public boolean isNoPrintErr() {
		return noPrintErr;
	}

	public void setNoPrintErr(boolean noPrintErr) {
		this.noPrintErr = noPrintErr;
	}
}
