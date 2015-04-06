package spr.board.utils.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;
/*
 * AbstractView를 상속받아 renderMergedOutputModel을 재정의하였습니다.
빈으로 등록하여 특정주소에서 서블릿이 이 클래스를 사용하기 위해서 @component를 사용했습니다. 
@component의 이름은 컨트롤러에서 사용하게 됩니다.
또한 Model을 받아오는데, 이 모델은 해쉬맵으로 되어있습니다. 따라서 컨트롤러에서 모델에 대한 키를 넘겨주면 그 키에
해당되는 값을 받아와야 하기 때문에 모델에서의 키와 같은 키로 지정해줘야 합니다.

+추가 
다운 받을 시 파일 이름을 변경해주고 싶은 경우를 상정하여 rename이라는 부분을 추가했습니다.
다운을 받는 컨트롤러 부분에서 HttpServletRequest를 통해서 이름을 보내오면 그것으로 파일 이름을 결정하게 합니다.
넘겨오지 않으면, 기존의 저장된 파일 이름을 그대로 사용합니다.
 */
@Component(value="fileDownloadView")
public class FileDownloadView  extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		File file = (File) model.get("fileDownload");
		response.setContentType("application/download; utf-8");
		response.setContentLength((int) file.length());

		String userAgent = request.getHeader("User-Agent");
		String rename = (String) request.getAttribute("fileName");
		String fileName = rename == null ? file.getName() : rename;

		if (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1) {   // MS IE 브라우저에서 한글 인코딩
			response.setHeader("Content-Disposition", "attachment; filename="
					+ java.net.URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "\\ ") + ";");
		} else {                                                                                              // 모질라나 오페라 브라우저에서 한글 인코딩​
			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1").replaceAll("\\+", "\\ ") + ";");
		}

		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
				}
			}
		}
		out.flush();
	}
}
