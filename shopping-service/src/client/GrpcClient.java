import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

    private void UnaryCall() {

    }


    private ServerStreaming() {

    }

    private ClientStreaming() {

    }

    private BiDirectional() {

    }

    private TimeLimitCall() {

    }

    public static void main(String[] args) {
        System.out.println("Starting Grpc Client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 20021).usePlaintext().build();

        System.out.println("In Grpc we can make following type of call");
        System.out.println("1 - Unary Call");
        System.out.println("2 - Server Streaming call");
        System.out.println("3 - Client Streaming Call");
        System.out.println("4 - Bidirectional Call");
        System.out.println("* - Unary call with shutdown");

        channel.shutdown();
        System.out.println("Shutting down grpc...");

    }
}
