package md5;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

//public class Temp {
//
//    public BaseResponseVo<Void> getIncrePackage() {
//
//        //查询需要生成差分包版本列表
//        List<AppIncreVersionVo> appIncreVersionVos = queryIncrePackageList();
//        if (CollectionUtils.isEmpty(appIncreVersionVos)){
//            return BaseResponseVo.success();
//        }
//        log.info("需生成的差分包列表："+appIncreVersionVos);
//        try{
//            // 得到最新的版本信息
//            AppVersionControl latestVersion = getLatestVersion(YADEA_SMART_TRAFFIC.getCode(), AppTypeEnum.ANDROID.getCode());
//            String appLatestVersion = latestVersion.getAppVersion();
//            String latestUrl = latestVersion.getZipDownloadUrl();
//            String targetPath = this.getClass().getResource(defaultPath).getPath();
////            File latestFile = new File(targetPath + appLatestVersion + ".apk");
//            // 1.下载最新版本到本地
//            log.info("下载最新版本到本地");
//            //File latest = HttpUtil.downloadFileFromUrl(latestUrl, latestFile);
//            byte[] latestBytes = HttpUtil.downloadBytes(latestUrl);
//            log.info("下载最新版本完成");
////            FileInputStream oldIn = new FileInputStream(latest);
////            byte[] latestBytes = new byte[(int) latest.length()];
////            oldIn.read(latestBytes);
////            oldIn.close();
//            // 2.得到最新MD5
////            String latestMd5 = MD5Util.file2MD5(latest);
//            String latestMd5 = MD5Util.getMD5String(latestBytes);
//            log.info("最新版本md5："+latestMd5);
//            // 3.遍历需要生成差分包的版本
//            for (int i = 0; i < appIncreVersionVos.size(); i++) {
////                Thread.sleep(1000);
//                AppIncreVersionVo appIncreVersionVo = appIncreVersionVos.get(i);
//                String appCurrentVersion = appIncreVersionVo.getAppVersion();
//                String currentUrl = appIncreVersionVo.getZipDownloadUrl();
////                File currentFile = new File(targetPath + appCurrentVersion + ".apk");
//                File increFile = new File(targetPath  +"雅迪智行"+ appCurrentVersion + "incre.apk");
//                // 3.1 下载当前版本到本地
//                log.info("下载当前版本到本地");
////                File current = HttpUtil.downloadFileFromUrl(currentUrl, currentFile);
//                byte[] currentBytes = HttpUtil.downloadBytes(currentUrl);
//                log.info("下载当前版本完成");
////                FileInputStream currentIn = new FileInputStream(current);
////                byte[] currentBytes = new byte[(int) current.length()];
////                currentIn.read(currentBytes);
////                currentIn.close();
//                // 3.2 生成差分包
//                log.info("生成差分包开始");
//                FileOutputStream out = new FileOutputStream(increFile);
//                Diff.diff(currentBytes,latestBytes,out);
//                out.close();
//                log.info("生成差分包结束");
//                // 3.3 得到差分包的md5
//                String incrMd5 = MD5Util.getFileMD5String(increFile);
//                log.info("差分包MD5:" +incrMd5);
//                // 3.4 上传差分包
//                OSSUploadFileReq req = new OSSUploadFileReq();
//                req.setFileType(IOS_DIFF_PACKAGE.getType());
//                log.info(increFile.getAbsolutePath().replace("\\","/"));
//                req.setFilePath(increFile.getAbsolutePath().replace("\\","/"));
//                AppIncreVersionDto appIncreVersionDto = new AppIncreVersionDto();
//                appIncreVersionDto.setId(appIncreVersionVo.getId());
//                appIncreVersionDto.setTargetVersion(appLatestVersion);
//                appIncreVersionDto.setUpdateType(INCRE_UPDATE.getCode());
//                appIncreVersionDto.setIncreMd5(incrMd5);
//                appIncreVersionDto.setTargetMd5(latestMd5);
//                req.setAppIncreVersionDto(appIncreVersionDto);
//                //可能熔断超时，异步更新数据库
//                aliOSSFeignService.uploadFile(req);
//                log.info("上传差分包");
//                //3.6 删除当前版本和差分版本包
////                del(currentFile);
//                del(increFile);
//            }
//            // 4.删除最新包
////            del(latestFile);
//            return BaseResponseVo.success();
//        }catch(Exception e){
//            log.warn("---生成差分包异常:{}",e);
//            return BaseResponseVo.fail(INCRE_PACKAGE_FAILED);
//        }
//    }
//}

