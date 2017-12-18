import org.apache.http.HttpHeaders
import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.MediaType

Contract.make {
  description "should create a new reservation, and return with a saved one."

  request {
    url "/reservations"
    method POST()
    headers {
      header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
    }
    body([name: "test", table: "1A", headCount: 1])
  }

  response {
    status 201
    headers {
      header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
    }
    body([id: 1L, name: "test", table: "1A", headCount: 1])
  }
}
