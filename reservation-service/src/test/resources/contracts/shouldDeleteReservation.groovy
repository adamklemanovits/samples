import org.springframework.cloud.contract.spec.Contract

Contract.make {
  description "should delete reservation with the given id."

  request {
    url "/reservations/1"
    method DELETE()
  }

  response {
    status 200
  }
}
