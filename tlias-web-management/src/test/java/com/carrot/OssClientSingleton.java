package com.carrot;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.internal.OSSHeaders;
import com.aliyun.oss.model.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class OssClientSingleton {
    private OssClientSingleton() {
    }

    // 静态内部类实现单例（线程安全）
    private static class SingletonHolder {
        private static final OSS INSTANCE = create();

        private static OSS create() {
            try {
                // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
                String endpoint = "oss-cn-beijing.aliyuncs.com";
                // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
                String region = "cn-beijing";

                ClientBuilderConfiguration config = new ClientBuilderConfiguration();
                // 显式声明使用 V4 签名算法
                config.setSignatureVersion(SignVersion.V4);

                //从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
                CredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

                // 构建OSS客户端
                return OSSClientBuilder.create()
                        .endpoint(endpoint)
                        .credentialsProvider(credentialsProvider)
                        .clientConfiguration(config)
                        .region(region)
                        .build();
            } catch (Exception e) {
                throw new RuntimeException("OSS客户端初始化失败", e);
            }
        }
    }

    // 获取单例实例
    public static OSS getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // 主函数测试PutObject操作
    public static void main(String[] args) throws IOException {
        // 获取单例OSS客户端
        OSS ossClient = OssClientSingleton.getInstance();

        // 填写Bucket名称，例如examplebucket。
        String bucketName = "javaweb-ai-tlias-carrot";
        // 填写不包含Bucket名称在内的Object完整路径，例如testfolder/exampleobject.txt。
        String objectKey = "testfolder/exampleobject.txt";

        try {
            // 填写字符串。
            File file = new File("C:\\Users\\辍二\\Desktop\\新建文本文档.txt");
            byte[] content = Files.readAllBytes(file.toPath());

            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectKey, new ByteArrayInputStream(content));

            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            metadata.setObjectAcl(CannedAccessControlList.Private);
            putObjectRequest.setMetadata(metadata);

            // 上传字符串。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            // 打印上传结果。
            System.out.println("文件上传成功！");
            System.out.println("ETag: " + result.getETag());
            System.out.println("请求ID: " + result.getRequestId());
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            // 在单例模式下，不建议在每次操作后关闭client（保持连接复用），避免影响后续使用。
            // 在明确OSSClient实例不再使用时（例如应用程序退出前），调用一次shutdown方法以释放资源。
            // ossClient.shutdown();
        }
    }
}