import org.apache.http.HttpHeaders
import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.MediaType

Contract.make {
  description "should return a reservation"

  request {
    url "/reservations/1"
    method GET()
  }

  response {
    status 200
    headers {
      header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
    }

    body(
            [id: 1L, name: "test", table: "1A", headCount: 1]
    )
  }
}
