package com.huawei.demo.demo;

import com.huaweicloud.sdk.core.auth.BasicCredentials;
import com.huaweicloud.sdk.core.auth.ICredential;
import com.huaweicloud.sdk.core.exception.ConnectionException;
import com.huaweicloud.sdk.core.exception.RequestTimeoutException;
import com.huaweicloud.sdk.core.exception.ServiceResponseException;
import com.huaweicloud.sdk.core.http.HttpConfig;
import com.huaweicloud.sdk.core.invoker.SyncInvoker;
import com.huaweicloud.sdk.rabbitmq.v2.RabbitMQClient;
import com.huaweicloud.sdk.rabbitmq.v2.model.ListInstancesDetailsRequest;
import com.huaweicloud.sdk.rabbitmq.v2.model.ListInstancesDetailsResponse;
import com.huaweicloud.sdk.rabbitmq.v2.region.RabbitMQRegion;
import com.huaweicloud.sdk.vpc.v3.VpcClient;
import com.huaweicloud.sdk.vpc.v3.model.ListSecurityGroupsRequest;
import com.huaweicloud.sdk.vpc.v3.model.ListSecurityGroupsResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

    /**
     * user name
     */
    public final static String UN = "hid_v_4dks_c0qhvz8u";

    /**
     * project id
     * 87408176acf348fbb17d7bfeacf62ccc
     * 0df9c06d7800f2852f6dc00035a09c33
     */
    public final static String PROJECT_ID = "87408176acf348fbb17d7bfeacf62ccc";

    /**
     * Access Key Id
     */
    public final static String AK = "";

    /**
     * Secret Access Key
     */
    public final static String SK = "";

    @Test
    void contextLoads() {
    }

    @Test
    public void testStartSdk() {
        String ak        = "{your ak string}";
        String sk        = "{your sk string}";
        String endpoint  = "{your endpoint string}";
        String projectId = "{your project id}";

        HttpConfig config = HttpConfig.getDefaultHttpConfig();
        config.withIgnoreSSLVerification(true);

        BasicCredentials auth = new BasicCredentials()
                .withAk(ak)
                .withSk(sk)
                .withProjectId(projectId);

        VpcClient vpcClient = VpcClient.newBuilder()
                .withHttpConfig(config)
                .withCredential(auth)
                .withEndpoint(endpoint)
                .build();

        SyncInvoker<ListSecurityGroupsRequest, ListSecurityGroupsResponse> responseSyncInvoker = vpcClient.listSecurityGroupsInvoker(new ListSecurityGroupsRequest());
        log.info("{}", responseSyncInvoker.toString());

    }

    @Test
    public void testRabbitMqSdk() {

        ICredential auth = new BasicCredentials()
                .withProjectId(PROJECT_ID)
                .withAk(AK)
                .withSk(SK);

        RabbitMQClient client = RabbitMQClient.newBuilder()
                .withCredential(auth)
                .withRegion(RabbitMQRegion.valueOf("cn-east-3"))
                .build();
        ListInstancesDetailsRequest request = new ListInstancesDetailsRequest();
        try {
            ListInstancesDetailsResponse response = client.listInstancesDetails(request);
            System.out.println(response.toString());
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (RequestTimeoutException e) {
            e.printStackTrace();
        } catch (ServiceResponseException e) {
            e.printStackTrace();
            System.out.println(e.getHttpStatusCode());
            System.out.println(e.getErrorCode());
            System.out.println(e.getErrorMsg());
        }
    }

}
