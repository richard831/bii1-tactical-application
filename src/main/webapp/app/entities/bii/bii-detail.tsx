import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './bii.reducer';
import { IBII } from 'app/shared/model/bii.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBIIDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class BIIDetail extends React.Component<IBIIDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { bIIEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            BII [<b>{bIIEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{bIIEntity.name}</dd>
            <dt>
              <span id="type">Type</span>
            </dt>
            <dd>{bIIEntity.type}</dd>
            <dt>
              <span id="biiId">Bii Id</span>
            </dt>
            <dd>{bIIEntity.biiId}</dd>
            <dt>
              <span id="detectionTimestamp">Detection Timestamp</span>
            </dt>
            <dd>{bIIEntity.detectionTimestamp}</dd>
            <dt>
              <span id="sourceId">Source Id</span>
            </dt>
            <dd>{bIIEntity.sourceId}</dd>
            <dt>
              <span id="detectionSystemName">Detection System Name</span>
            </dt>
            <dd>{bIIEntity.detectionSystemName}</dd>
            <dt>
              <span id="detectedValue">Detected Value</span>
            </dt>
            <dd>{bIIEntity.detectedValue}</dd>
            <dt>
              <span id="detectionContext">Detection Context</span>
            </dt>
            <dd>{bIIEntity.detectionContext}</dd>
            <dt>
              <span id="etc">Etc</span>
            </dt>
            <dd>{bIIEntity.etc}</dd>
            <dt>
              <span id="etcetc">Etcetc</span>
            </dt>
            <dd>{bIIEntity.etcetc}</dd>
          </dl>
          <Button tag={Link} to="/entity/bii" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/bii/${bIIEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ bII }: IRootState) => ({
  bIIEntity: bII.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BIIDetail);
